package volley_net.volley_net.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import volley_net.volley_net.entity.Game;
import volley_net.volley_net.entity.Period;
import volley_net.volley_net.entity.Score;
import volley_net.volley_net.entity.Team;
import volley_net.volley_net.payload.request.GameGenericRequest;
import volley_net.volley_net.payload.request.GameSpecificRequest;
import volley_net.volley_net.payload.request.BetFutureRequest;
import volley_net.volley_net.payload.request.WeekMaxRequest;
import volley_net.volley_net.payload.response.*;
import volley_net.volley_net.repository.GameRepository;
import volley_net.volley_net.repository.TeamRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    /**
     *
     * @param request
     * @return get partita da id partita
     */
    public ResponseEntity<?> get_game_specific(GameSpecificRequest request) {
        try {
            Game g = gameRepository.GetGameByIdGame(request.getId_game());
            if(g==null){
                return new ResponseEntity<>("nessuna partita trovata", HttpStatus.NOT_FOUND);
            }
            List<Score> punti= gameRepository.GetScoreFromIdGame(g.getId_game());
            List<TeamsGameSpecific> teams = new ArrayList<>();
            for(Score punto:punti){
                Team squadra = teamRepository.GetTeamByIdTeam(punto.getId_team().getId_team());

                List<Period> tempi= gameRepository.GetPeriodFromScore(punto.getId_score());
                PeriodsTeamsGameSpecific dati_tempo= new PeriodsTeamsGameSpecific(tempi.get(0).getPoints(),tempi.get(1).getPoints(),tempi.get(2).getPoints(),tempi.get(3).getPoints(),tempi.get(4).getPoints());

                TeamsGameSpecific dati_squadra = new TeamsGameSpecific(squadra.getId_team(), squadra.getName(), squadra.getLogo(), squadra.isNational(), punto.isHome(), punto.getSets(), dati_tempo);
                teams.add(dati_squadra);
            }
            GetGameSpecificResponse response= new GetGameSpecificResponse(g.getId_game(),g.getDate(),g.getTime(),g.getStatus(), g.getWeek(), teams);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("errore nel server", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     *
     * @param request
     * @return lista di partite di una giornata (week) di una lega di una stagione
     */
    public ResponseEntity<?> get_game_generic(GameGenericRequest request) {

        try{
            List<Game> partite = gameRepository.GetListOfGameByWeek(request.getWeek(), request.getSeason(), request.getId_league());
            if(partite.isEmpty()){
                return new ResponseEntity<>("nessuna partita trovata", HttpStatus.NOT_FOUND);
            }

            List<GetGameGenericResponse> response = new ArrayList<>();

            for(Game partita:partite){

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

                response.add(new GetGameGenericResponse(partita.getId_game(),partita.getDate(),partita.getTime(),partita.getStatus(),partita.getWeek(),partita.getId_league().getName(),teams));

            }
            return new ResponseEntity<>(response, HttpStatus.OK);



        }catch (Exception e){
            return new ResponseEntity<>("errore nel server", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     *
     * @return get scommesse per i due giorni successivi a oggi
     */
    public ResponseEntity<?> bets_page() {
       try{
           LocalDate data=LocalDate.now().plusDays(2);
           List<Game> partite= gameRepository.GetGameAfterDate(data);
           if(partite.isEmpty()){
               return new ResponseEntity<>("nessuna partita futura trovata", HttpStatus.NOT_FOUND);
           }
           log.info(partite.toString());
           List<BetPageResponse> response = new ArrayList<>();
           for(Game partita:partite){
               List<Score> punti= gameRepository.GetScoreFromIdGame(partita.getId_game());
               List<TeamsBetPage> teams = new ArrayList<>();
               for(Score punto:punti){

                    Team t= teamRepository.GetTeamByIdTeam(punto.getId_team().getId_team());
                    float odd;
                    if(punto.isHome()) {
                        if(partita.getHome_odds()==null){
                            odd=0.0f;
                        }else{
                            odd= partita.getHome_odds();
                        }

                    }else{
                        if(partita.getAway_odds()==null){
                            odd=0.0f;
                        }else{
                            odd= partita.getAway_odds();
                        }
                    }
                   teams.add(new TeamsBetPage(t.getId_team(), t.getName(), t.getLogo(), t.isNational(), punto.isHome(), punto.getSets(),odd));

               }
               response.add(new BetPageResponse(partita.getId_game(),partita.getDate(),partita.getTime(), partita.getStatus(), partita.getWeek(),teams));
           }
           return new ResponseEntity<>(response, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>("non ho trovato niente", HttpStatus.BAD_REQUEST);
       }
    }



    /**
     *
     * @return ultimo game giocato di superlega
     */
    public ResponseEntity<?> get_default_game() {
        try {
            Game g = gameRepository.GetGameRecente(97);
            if(g==null){
                return new ResponseEntity<>("partita di default non trovato", HttpStatus.NOT_FOUND);
            }
            List<TeamsGameGenerics> teams = new ArrayList<>();
                List<Score> punti = gameRepository.GetScoreFromIdGame(g.getId_game());

                for (Score punto : punti) {
                    Team squadra = teamRepository.GetTeamByIdTeam(punto.getId_team().getId_team());
                    TeamsGameGenerics dati = new TeamsGameGenerics(squadra.getId_team(), squadra.getName(), squadra.getLogo(), squadra.isNational(), punto.isHome(), punto.getSets());
                    teams.add(dati);
                }


           GetGameGenericResponse response=new GetGameGenericResponse(g.getId_game(),g.getDate(),g.getTime(),g.getStatus(),g.getWeek(),g.getId_league().getName(),teams);


            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     *
     * @param request
     * @return numero della giornata massima di uan lega di una stagione
     */
    public ResponseEntity<?> get_week_max(WeekMaxRequest request) {

        try{
           Integer week= gameRepository.MaxWeek(request.getSeason(),request.getId_league());
            if(week==null){
                return new ResponseEntity<>("nessuna giornata trovata", HttpStatus.NOT_FOUND);
            }
           return new ResponseEntity<>(new GetWeekMaxResponse(week), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("errore nel server", HttpStatus.BAD_REQUEST);
        }
    }



    /**
     *
     * @param request
     * @return restituisco una partita dall'id_game
     */
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

    /**
     *
     * @param id_game
     * @return restituisco uno score dall'id_game
     */
    private List<Score> get_score(int id_game){
        try{
           List<Score> elenco= gameRepository.GetScoreFromIdGame(id_game);
            if(elenco.isEmpty()){
                return null;
            }
            return elenco;
        }catch (Exception e){
            return null;
        }
    }
}
