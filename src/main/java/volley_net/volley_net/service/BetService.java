package volley_net.volley_net.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import volley_net.volley_net.entity.*;
import volley_net.volley_net.payload.request.BetFutureRequest;
import volley_net.volley_net.payload.request.CreateBetRequest;
import volley_net.volley_net.payload.request.UserRequest;
import volley_net.volley_net.payload.response.BetPageResponse;
import volley_net.volley_net.payload.response.BetUserResponse;
import volley_net.volley_net.payload.response.TeamsBetPage;
import volley_net.volley_net.repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BetService {

    /**
     * operazioni del database di bet {@link BetRepository}
     */
    private final BetRepository betRepository;
    /**
     * {@link  TokenService} legato all'utente
     */
    private final TokenService tokenService;
    /**
     * operazioni del database di user {@link UserRepository}
     */
    private final UserRepository userRepository;
    /**
     * operazioni del database di game {@link GameRepository}
     */
    private final GameRepository gameRepository;
    /**
     * operazioni del database di team {@link TeamRepository}
     */
    private final TeamRepository teamRepository;
    /**
     * operazioni del database di score {@link ScoreRepository}
     */
    private final ScoreRepository scoreRepository;

    /**
     * metodo per salvare una nuova scommessa di un utente sul db
     * @param request {@link CreateBetRequest}
     * @return ResponseEntity con l'esito dell'operazione di salvataggio
     */
    public ResponseEntity<?> salva_bet(CreateBetRequest request) {
        try {
            Bet b = crea_bet(request);
            if(b==null){
               return new ResponseEntity<>("errore nella creazione della scommessa", HttpStatus.BAD_REQUEST);
            }
            betRepository.save(b);
            return new ResponseEntity<>("scommessa creata", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("errore del server", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * metodo per creare la scommessa da salvare
     * @param request {@link CreateBetRequest}
     * @return {@link Bet} salvata nel db o null
     */
    private Bet crea_bet(CreateBetRequest request){
        try {
            UserToken token = tokenService.getUserIdFromToken(request.getToken());
            User u = userRepository.getUserById(token.getId_token());
            Game g = gameRepository.GetGameByIdGame(request.getId_game());
            Team t = teamRepository.GetTeamByIdTeam(request.getId_team());
            Bet b = new Bet(g, u, t,false);
            return b;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * metodo per avere le scommesse di una giornata di una lega di una stagione
     * @param request {@link BetFutureRequest}
     * @return lista di {@link BetPageResponse} contenente le scommesse
     */
    public ResponseEntity<?> get_future_bets(BetFutureRequest request) {
        try {
            List<Bet> scommesse= betRepository.ListOfBets(request.getWeek(), request.getSeason(), request.getId_league());
            List<Game> partite =new ArrayList<>();
            List<Integer> segnati = new ArrayList<>();
            for (Bet scommessa:scommesse){
                Game g =gameRepository.GetGameByIdGame(scommessa.getId_game().getId_game());
                if(!segnati.contains(g.getId_game())) {
                    partite.add(g);
                    segnati.add(g.getId_game());
                }
            }
            List<BetPageResponse> response = new ArrayList<>();
            for (Game partita : partite) {
                List<Score> punti = gameRepository.GetScoreFromIdGame(partita.getId_game());
                List<TeamsBetPage> teams = new ArrayList<>();
                for (Score punto : punti) {

                    Team t = teamRepository.GetTeamByIdTeam(punto.getId_team().getId_team());
                    float odd;
                    if (punto.isHome()) {
                        if (partita.getHome_odds() == null) {
                            odd = 0.0f;
                        } else {
                            odd = partita.getHome_odds();
                        }

                    } else {
                        if (partita.getAway_odds() == null) {
                            odd = 0.0f;
                        } else {
                            odd = partita.getAway_odds();
                        }
                    }
                    teams.add(new TeamsBetPage(t.getId_team(), t.getName(), t.getLogo(), t.isNational(), punto.isHome(), punto.getSets(), odd));

                }
                response.add(new BetPageResponse(partita.getId_game(), partita.getDate(), partita.getTime(), partita.getStatus(), partita.getWeek(),partita.getId_league().getName(), teams));


            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("errore nella chiamata per le scommesse", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * metodo per avere una lista di scommesse fatte da un utente
     * @param request {@link UserRequest}
     * @return lista di bet {@link List<BetUserResponse>} fatto dall'utente
     */
    public ResponseEntity<?> get_bets_user(UserRequest request){
    try{
        UserToken id_utente = tokenService.getUserIdFromToken(request.getToken());
        if(id_utente==null){
            return  new ResponseEntity<>("utente non trovato", HttpStatus.NOT_FOUND);
        }
        List<Bet> elenco =betRepository.ListOfBetsUser(id_utente.getId_token());
        if(!elenco.isEmpty()){
            List<BetUserResponse> response = new ArrayList<>();
            for(Bet b:elenco){
                Game g= gameRepository.GetGameByIdGame(b.getId_game().getId_game());
                if(g!=null) {
                    List<Score> scores=gameRepository.GetScoreFromIdGame(g.getId_game());
                    if(!scores.isEmpty() && scores.size()==2) {
                        String logo_s="";
                        String logo_a="";
                        String name_s="";
                        String name_a="";
                        for(Score s:scores){

                            if(s.getId_team().getId_team()==b.getId_team().getId_team()){
                                logo_s=s.getId_team().getLogo();
                                name_s=s.getId_team().getName();
                            }else{
                                logo_a=s.getId_team().getLogo();
                                name_a=s.getId_team().getName();
                            }
                        }

                        response.add(new BetUserResponse(g.getId_game(),g.getId_league().getName(),logo_s,logo_a,name_s,name_a));

                    }
                }
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return  new ResponseEntity<>("nessuna scommessa trovata", HttpStatus.NOT_FOUND);
    }catch (Exception e){
        return new ResponseEntity<>("errore nella chiamata per le scommesse", HttpStatus.BAD_REQUEST);
    }
    }
}
