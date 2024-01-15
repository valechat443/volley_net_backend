package volley_net.volley_net.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import volley_net.volley_net.entity.*;
import volley_net.volley_net.payload.request.BetFutureRequest;
import volley_net.volley_net.payload.request.CreateBetRequest;
import volley_net.volley_net.payload.response.BetPageResponse;
import volley_net.volley_net.payload.response.TeamsBetPage;
import volley_net.volley_net.repository.BetRepository;
import volley_net.volley_net.repository.GameRepository;
import volley_net.volley_net.repository.TeamRepository;
import volley_net.volley_net.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BetService {

    private final BetRepository betRepository;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    /**
     * metodo per salvare una nuova scomessa di un utente sul db
     * @param request
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
     * @param request
     * @return scommessa salvata nel db o null
     */
    private Bet crea_bet(CreateBetRequest request){
        try {
            UserToken token = tokenService.getUserIdFromToken(request.getToken());
            User u = userRepository.getUserById(token.getId_token());
            Game g = gameRepository.GetGameByIdGame(request.getId_game());
            Team t = teamRepository.GetTeamByIdTeam(request.getId_team());
            Bet b = new Bet(g, u, t);
            return b;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * metodo per avere le scommesse di una giornata di una lega di una stagione
     * @param request
     * @return lista di BetPageResponse contenente le scommesse
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
                response.add(new BetPageResponse(partita.getId_game(), partita.getDate(), partita.getTime(), partita.getStatus(), partita.getWeek(), teams));


            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("errore nella chiamata per le scommesse", HttpStatus.BAD_REQUEST);
        }
    }
}
