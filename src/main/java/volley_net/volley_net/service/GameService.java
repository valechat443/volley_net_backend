package volley_net.volley_net.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import volley_net.volley_net.entity.Game;
import volley_net.volley_net.entity.Score;
import volley_net.volley_net.entity.Team;
import volley_net.volley_net.payload.request.GameGenericRequest;
import volley_net.volley_net.payload.request.GameSpecificRequest;
import volley_net.volley_net.payload.request.BetFutureRequest;
import volley_net.volley_net.payload.request.WeekMaxRequest;
import volley_net.volley_net.payload.response.GetGameGenericResponse;
import volley_net.volley_net.payload.response.TeamsGameGenerics;
import volley_net.volley_net.repository.GameRepository;
import volley_net.volley_net.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    /**
     *get partita da id partita
     *
     *
     */
    private ResponseEntity<?> get_game_specific(GameSpecificRequest request) {
        return null;
    }
    /**
     *lista di partite di una giornata (week) di una lega di una stagione
     *
     */
    public ResponseEntity<?> get_game_generic(GameGenericRequest request) {

        try{
            List<Game> partite = gameRepository.GetListOfGame(request.getSeason(), request.getId_league());
            //log.info(partite.toString());

            List<GetGameGenericResponse> response = new ArrayList<>();
            int count=0;
            for(Game partita:partite){
                count++;
                log.info(String.valueOf(partita.getStatus()));
                List<TeamsGameGenerics> teams = new ArrayList<>();
                if(!partita.getStatus().equals("Not Started")){
                    List<Score> punti = gameRepository.GetScoreFromIdGame(partita.getId_game());



                    for (Score punto : punti) {
                        Team squadra = teamRepository.GetTeamByIdTeam(punto.getId_team().getId_team());
                        TeamsGameGenerics dati = new TeamsGameGenerics(squadra.getId_team(), squadra.getName(), squadra.getLogo(), squadra.isNational(), punto.isHome(), punto.getSets());
                        teams.add(dati);
                    }
                }else{
                    TeamsGameGenerics dati = new TeamsGameGenerics(0,"","",false,false,0);
                    teams.add(dati);
                }

                response.add(new GetGameGenericResponse(partita.getId_game(),partita.getDate(),partita.getTime(),partita.getStatus(),partita.getWeek(),teams));

            }
            return new ResponseEntity<>(response, HttpStatus.OK);


            //return new ResponseEntity<>("ok", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("errore nel server", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     *get scommesse per i due giorni successivi a oggi
     *
     */
    private ResponseEntity<?> bets_page() {
        return null;
    }

    /**
     *scommesse di una giornata di una lega di una stagione
     *
     */
    private ResponseEntity<?> get_future_bets(BetFutureRequest request) {
        return null;
    }

    /**
     *ultimo game giocato di superlega
     *
     */
    private ResponseEntity<?> get_default_game() {
        return null;
    }

    /**
     *numero della giornata massima di uan lega di una stagione
     *
     */
    public ResponseEntity<?> get_week_max(WeekMaxRequest request) {

        try{
           int week= gameRepository.MaxWeek(request.getSeason(),request.getId_league());

           return new ResponseEntity<>(week, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("errore nel server", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     *creo una bet di un certo utente che scommette su una certa squadra di una partita
     *
     */
    private ResponseEntity<?> create_bet() {
        return null;
    }

    public ResponseEntity<?> getGameById(GameSpecificRequest request){
        try{
            Game g = gameRepository.GetGameByIdGame(request.getId_game());
            if(g==null){
                return new ResponseEntity<>("nessuna partita trovata", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(g, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("errore nel server", HttpStatus.BAD_REQUEST);
        }
    }
    private List<Score> get_score(int id_game){
        try{
           List<Score> elenco= gameRepository.GetScoreFromIdGame(id_game);
            if(elenco==null){
                return null;
            }
            return elenco;
        }catch (Exception e){
            return null;
        }
    }
}
