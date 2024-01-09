package volley_net.volley_net.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import volley_net.volley_net.entity.*;
import volley_net.volley_net.payload.response.BetPageResponse;
import volley_net.volley_net.payload.request.GameGenericRequest;
import volley_net.volley_net.payload.request.GameSpecificRequest;

import volley_net.volley_net.payload.request.WeekMaxRequest;
import volley_net.volley_net.payload.response.*;
import volley_net.volley_net.repository.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final ScoreRepository scoreRepository;
    private final PeriodRepository periodRepository;

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
                List<Integer> points= new ArrayList<>();
                for(Period t:tempi){
                    points.add(t.getPoints());
                }


                TeamsGameSpecific dati_squadra = new TeamsGameSpecific(squadra.getId_team(), squadra.getName(), squadra.getLogo(), squadra.isNational(), punto.isHome(), punto.getSets(), points);
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

            for(Game partita:partite) {

                log.info(String.valueOf(partita.getStatus()));
                List<TeamsGameGenerics> teams = new ArrayList<>();
                if (!partita.getStatus().equals("Not Started")) {

                    List<Score> punti = gameRepository.GetScoreFromIdGame(partita.getId_game());
                    if(!punti.isEmpty()){


                        for (Score punto : punti) {
                            Team squadra = teamRepository.GetTeamByIdTeam(punto.getId_team().getId_team());
                            TeamsGameGenerics dati = new TeamsGameGenerics(squadra.getId_team(), squadra.getName(), squadra.getLogo(), squadra.isNational(), punto.isHome(), punto.getSets());
                            teams.add(dati);
                        }
                        response.add(new GetGameGenericResponse(partita.getId_game(),partita.getDate(),partita.getTime(),partita.getStatus(),partita.getWeek(),partita.getId_league().getName(),teams));

                    }

                }else{
                    TeamsGameGenerics dati = new TeamsGameGenerics(0,"","",false,false,0);
                    teams.add(dati);
                    teams.add(dati);
                }


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

           List<BetPageResponse> response = new ArrayList<>();
           for(Game partita:partite){

               List<Score> punti= gameRepository.GetScoreFromIdGame(partita.getId_game());
               List<TeamsBetPage> teams = new ArrayList<>();
               if(!punti.isEmpty()) {
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
            List<Game> elenco = gameRepository.GetListGameRecenti(97);
            int count=0;
            GetGameGenericResponse response=new GetGameGenericResponse();
            //Game g = gameRepository.GetGameRecente(97);
            for (Game g:elenco) {
                List<Score> punti = gameRepository.GetScoreFromIdGame(g.getId_game());
                if(g==null || punti.isEmpty()){
                    if(count>=5){
                        return  new ResponseEntity<>("nessun game recente disponibile", HttpStatus.NOT_FOUND);
                    }
                   count++;
                   continue;
                }
                List<TeamsGameGenerics> teams = new ArrayList<>();


                    for (Score punto : punti) {
                        Team squadra = teamRepository.GetTeamByIdTeam(punto.getId_team().getId_team());
                        TeamsGameGenerics dati = new TeamsGameGenerics(squadra.getId_team(), squadra.getName(), squadra.getLogo(), squadra.isNational(), punto.isHome(), punto.getSets());
                        teams.add(dati);
                    }

                response=new GetGameGenericResponse(g.getId_game(),g.getDate(),g.getTime(),g.getStatus(),g.getWeek(),g.getId_league().getName(),teams);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
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
           List<String> week= gameRepository.ListOfWeek(request.getSeason(),request.getId_league());
           String response="";
            if(week==null){
                return new ResponseEntity<>("nessuna giornata trovata", HttpStatus.NOT_FOUND);
            }
            if(week.contains("Final"))
            {
                return new ResponseEntity<>(new GetWeekMaxResponse("Semi-finals"), HttpStatus.OK);
            }
            else if(week.contains("Semi-finals"))
            {
                return new ResponseEntity<>(new GetWeekMaxResponse("Final"), HttpStatus.OK);
            }
            List<Integer> nums= new ArrayList<>();
            for (String s : week) {
                try {
                    nums.add(Integer.parseInt(s));
                } catch (NumberFormatException e) {
                 log.info(e.getMessage());
                }
            }

           return new ResponseEntity<>(new GetWeekMaxResponse(String.valueOf(Collections.max(nums))), HttpStatus.OK);
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

    private  Game salva_game(JSONObject game){
        try{
        DateTimeFormatter giorno = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        DateTimeFormatter ora = DateTimeFormatter.ofPattern("HH:mm");
        League l = new League( game.getJSONObject("league").getInt("id"));
        Game g = new Game(game.getInt("id"),
                l, LocalDate.parse(game.getString("date"), giorno),
                LocalTime.parse(game.getString("time"),ora),
                game.getString("timezone"),
                game.getJSONObject("status").getString("long"),
                null,null,game.getString("week"));
        gameRepository.save(g);
        return g;
        }catch (Exception e){
            return null;
        }
    }
//non mette league 94
    public ResponseEntity<?> salva_periods(WeekMaxRequest request){
        try{



            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-rapidapi-key", "a4d9f5a5e67beba13075382ca1379f3a");
            headers.add("x-rapidapi-host", "v1.volleyball.api-sports.io");

            HttpEntity<String> entity = new HttpEntity<String>(headers);

            ResponseEntity<String> cose = restTemplate.exchange(
                    "https://v1.volleyball.api-sports.io/games?league="+String.valueOf(request.getId_league())+"&season="+String.valueOf(request.getSeason()),
                    HttpMethod.GET,
                    entity,
                    String.class);

            JSONObject jason = new JSONObject(cose.getBody());
            JSONArray  response = jason.getJSONArray("response");


            int count=0;
            for (int i = 0; i < response.length(); i++) {
                JSONObject game = response.getJSONObject(i);
                Game g = salva_game(game);
                log.info( String.valueOf(count++));
                if (g != null) {
                    for (int j = 0; j < game.getJSONObject("scores").length(); j++) {
                        Score score= new Score();
                        if (j == 0) {
                            Team t = new Team(game.getJSONObject("teams").getJSONObject("home").getInt("id"));
                            try {
                                score = new Score(g, t, true, game.getJSONObject("scores").getInt("home"));
                            }catch(Exception e){
                                score = new Score(g, t, true, null);
                            }
                            log.info(String.valueOf(score.getSets()));

                            if(score.getSets()!=null) {
                                Score s =scoreRepository.save(score);
                                JSONObject periods = game.getJSONObject("periods");
                                for (int k = 1; k < periods.length()+1; k++) {

                                    Integer punti=null;
                                    try{
                                    if(k==1){
                                        punti=periods.getJSONObject("first").getInt("home");
                                    }else if(k==2){
                                        punti=periods.getJSONObject("second").getInt("home");
                                    }else if(k==3){
                                        punti=periods.getJSONObject("third").getInt("home");
                                    }else if(k==4){
                                        punti=periods.getJSONObject("fourth").getInt("home");
                                    }else if(k==5){
                                        punti=periods.getJSONObject("fifth").getInt("home");
                                    }
                                }catch (Exception e){
                                    punti=null;
                                }
                                    if(punti!=null) {
                                        Period p = new Period(s, punti, String.valueOf(k));
                                        periodRepository.save(p);
                                    }
                                }
                            }



                        } else {
                            Team t = new Team(game.getJSONObject("teams").getJSONObject("away").getInt("id"));
                            try {
                                score = new Score(g, t, true, game.getJSONObject("scores").getInt("away"));
                            }catch(Exception e){
                                score = new Score(g, t, true, null);
                            }
                            if(score.getSets()!=null) {
                                Score s =scoreRepository.save(score);
                                JSONObject periods = game.getJSONObject("periods");
                                for (int k = 1; k < periods.length()+1; k++) {
                                    Integer punti=null;
                                    try {
                                        if (k == 1) {
                                            punti = periods.getJSONObject("first").getInt("away");
                                        } else if (k == 2) {
                                            punti = periods.getJSONObject("second").getInt("away");
                                        } else if (k == 3) {
                                            punti = periods.getJSONObject("third").getInt("away");
                                        } else if (k == 4) {
                                            punti = periods.getJSONObject("fourth").getInt("away");
                                        } else if (k == 5) {
                                            punti = periods.getJSONObject("fifth").getInt("away");
                                        }
                                    }catch (Exception e){
                                        punti=null;
                                    }
                                    if(punti!=null) {
                                        Period p = new Period(s, punti, String.valueOf(k));
                                        periodRepository.save(p);
                                    }

                                }
                            }

                        }




                    }
                }
            }

            return new ResponseEntity<>("ok", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
