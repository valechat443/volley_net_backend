package volley_net.volley_net.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import volley_net.volley_net.entity.*;
import volley_net.volley_net.payload.request.SaveGameRequest;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

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
     * operazioni del database di period {@link PeriodRepository}
     */
    private final PeriodRepository periodRepository;
    /**
     * operazioni del database di team_season {@link TeamSeasonRepository}
     */
    private final TeamSeasonRepository teamSeasonRepository;
    /**
     * servizi legati a i file json restituiti da APi sport {@link JsonService}
     */
    private final JsonService jsonService;


    /**
     * metodo per avere i dati dettagliati di una partita specifica
     * @param request {@link GameSpecificRequest}
     * @return {@link GetGameSpecificResponse} con i dati della partita
     */
    public ResponseEntity<?> get_game_specific(GameSpecificRequest request) {
        try {
            Game g = gameRepository.GetGameByIdGame(request.getId_game());
            if (g == null) {
                return new ResponseEntity<>("nessuna partita trovata", HttpStatus.NOT_FOUND);
            }
            List<Score> punti = gameRepository.GetScoreFromIdGame(g.getId_game());
            List<TeamsGameSpecific> teams = new ArrayList<>();
            for (Score punto : punti) {
                Team squadra = teamRepository.GetTeamByIdTeam(punto.getId_team().getId_team());


                List<Period> tempi = gameRepository.GetPeriodFromScore(punto.getId_score());
                TeamsGameSpecific dati_squadra = new TeamsGameSpecific();
                if(!tempi.isEmpty()){
                    List<Integer> points = new ArrayList<>();
                    for (Period t : tempi) {
                        points.add(t.getPoints());
                    }
                   dati_squadra = new TeamsGameSpecific(squadra.getId_team(), squadra.getName(), squadra.getLogo(), squadra.isNational(), punto.isHome(), punto.getSets(), points);

                }else{
                    dati_squadra = new TeamsGameSpecific(squadra.getId_team(), squadra.getName(), squadra.getLogo(), squadra.isNational(), punto.isHome(), punto.getSets(), new ArrayList<>());

                }

                teams.add(dati_squadra);



            }
            GetGameSpecificResponse response = new GetGameSpecificResponse(g.getId_game(), g.getDate(), g.getTime(), g.getStatus(), g.getWeek(), teams);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("errore nel server", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * metodo per avere una lista di partite di una giornata (week) di una lega di una stagione
     * @param request {@link  GameGenericRequest}
     * @return lista di {@link GetGameGenericResponse} contenete una serie di partite
     */
    public ResponseEntity<?> get_game_generic(GameGenericRequest request) {

        try {
            List<Game> partite = gameRepository.GetListOfGameByWeek(request.getWeek(), request.getSeason(), request.getId_league());
            if (partite.isEmpty()) {
                return new ResponseEntity<>("nessuna partita trovata", HttpStatus.NOT_FOUND);
            }

            List<GetGameGenericResponse> response = new ArrayList<>();

            for (Game partita : partite) {

                log.info(String.valueOf(partita.getStatus()));
                List<TeamsGameGenerics> teams = new ArrayList<>();
                if (!partita.getStatus().equals("Not Started")) {

                    List<Score> punti = gameRepository.GetScoreFromIdGame(partita.getId_game());
                    if (!punti.isEmpty()) {


                        for (Score punto : punti) {
                            Team squadra = teamRepository.GetTeamByIdTeam(punto.getId_team().getId_team());
                            TeamsGameGenerics dati = new TeamsGameGenerics(squadra.getId_team(), squadra.getName(), squadra.getLogo(), squadra.isNational(), punto.isHome(), punto.getSets());
                            teams.add(dati);
                        }
                        response.add(new GetGameGenericResponse(partita.getId_game(), partita.getDate(), partita.getTime(), partita.getStatus(), partita.getWeek(), partita.getId_league().getName(), teams));

                    }

                } else {
                    TeamsGameGenerics dati = new TeamsGameGenerics(0, "", "", false, false, 0);
                    teams.add(dati);
                    teams.add(dati);
                }


            }
            return new ResponseEntity<>(response, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>("errore nel server", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * metodo per avere una lista di future partite (tra almeno due giorni) su cui scommettere
     * @return get scommesse {@link List<BetPageResponse>} per i due giorni successivi a oggi
     */
    public ResponseEntity<?> bets_page() {
        try {
            LocalDate data = LocalDate.now().plusDays(2);
            List<Game> partite = gameRepository.GetGameAfterDate(data);
            if (partite.isEmpty()) {
                return new ResponseEntity<>("nessuna partita futura trovata", HttpStatus.NOT_FOUND);
            }

            List<BetPageResponse> response = new ArrayList<>();
            for (Game partita : partite) {

                List<Score> punti = gameRepository.GetScoreFromIdGame(partita.getId_game());
                List<TeamsBetPage> teams = new ArrayList<>();
                if (!punti.isEmpty()) {
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
        } catch (Exception e) {
            return new ResponseEntity<>("non ho trovato niente", HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * metodo per restituire l'ultimo game disponibile della super lega
     * @return ultimo {@link Game} giocato di superlega
     */
    public ResponseEntity<?> get_default_game() {
        try {
            List<Game> elenco = gameRepository.GetListGameRecenti(97);
            int count = 0;
            GetGameGenericResponse response = new GetGameGenericResponse();
            //Game g = gameRepository.GetGameRecente(97);
            for (Game g : elenco) {
                List<Score> punti = gameRepository.GetScoreFromIdGame(g.getId_game());
                if (g == null || punti.isEmpty()) {
                    if (count >= 5) {
                        return new ResponseEntity<>("nessun game recente disponibile", HttpStatus.NOT_FOUND);
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

                response = new GetGameGenericResponse(g.getId_game(), g.getDate(), g.getTime(), g.getStatus(), g.getWeek(), g.getId_league().getName(), teams);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * metodo per restituire l'ultima giornata disponibile di una lega in una determinata stagione
     * @param request {@link WeekMaxRequest}
     * @return {@link GetWeekMaxResponse} contenente l'ultima giornata disponibile
     */
    public ResponseEntity<?> get_week_max(WeekMaxRequest request) {

        try {
            List<String> week = gameRepository.ListOfWeek(request.getSeason(), request.getId_league());
            String response = "";
            if (week == null) {
                return new ResponseEntity<>("nessuna giornata trovata", HttpStatus.NOT_FOUND);
            }
            if (week.contains("Final")) {
                return new ResponseEntity<>(new GetWeekMaxResponse("Final"), HttpStatus.OK);
            } else if (week.contains("Semi-finals")) {
                return new ResponseEntity<>(new GetWeekMaxResponse("Semi-finals"), HttpStatus.OK);
            }else if (week.contains("Quarter-finals")) {
                return new ResponseEntity<>(new GetWeekMaxResponse("Quarter-finals"), HttpStatus.OK);
            } else if (week.contains("3rd place")) {
                return new ResponseEntity<>(new GetWeekMaxResponse("3rd place"), HttpStatus.OK);
            }else if (week.contains("5rd place")) {
                return new ResponseEntity<>(new GetWeekMaxResponse("5rd place"), HttpStatus.OK);
            }
            List<Integer> nums = new ArrayList<>();
            for (String s : week) {
                try {
                    nums.add(Integer.parseInt(s));
                } catch (NumberFormatException e) {
                    log.info(e.getMessage());
                }
            }

            return new ResponseEntity<>(new GetWeekMaxResponse(String.valueOf(Collections.max(nums))), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("errore nel server", HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * metodo per restituire una partita dall'id_game
     * @param request {@link GameSpecificRequest}
     * @return un oggetto {@link Game}
     */
    public ResponseEntity<?> getGameById(GameSpecificRequest request) {
        try {
            Game g = gameRepository.GetGameByIdGame(request.getId_game());
            if (g == null) {
                return new ResponseEntity<>("nessuna partita trovata", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(g, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("errore nel server", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * metodo salvare una partita, con i suoi score e i suoi periods
     * @param request {@link SaveGameRequest}
     * @return messaggio per indicare se il salvataggio è andato a buon fine
     */
    public ResponseEntity<?> salva_partita_completa(SaveGameRequest request) {
        try {

            JSONObject jason = jsonService.chiamata("https://v1.volleyball.api-sports.io/games?league=" + String.valueOf(request.getId_league()) + "&season=" + String.valueOf(request.getSeason()));
            if (jason == null) {
                return null;
            }
            JSONArray response = jason.getJSONArray("response");

            Game game = new Game();
            List<Score> scores = new ArrayList<>();
            List<Period> periods= new ArrayList<>();

            for (int i = 0; i < response.length(); i++) {
                JSONObject g = response.getJSONObject(i);
                game = salva_game(g);

               if(game!=null){
                     scores= salva_score(g);

               }else{
                   return new ResponseEntity<>("errore salvataggio game", HttpStatus.NOT_MODIFIED);
               }
               log.info(String.valueOf(g.getInt("id")));
               if(!scores.isEmpty() && scores.size()==2 && scores.get(0).getSets()!=null){
                    periods=salva_periods(scores,g);
               }





            }
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("errore nel server", HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * metodo per salvare uan partita nel db
     * @param game oggetto json preso dalal risposta data da API sport
     * @return oggetto {@link Game} salvato nel db o null
     */
    private Game salva_game(JSONObject game) {
        try {
            DateTimeFormatter giorno = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            DateTimeFormatter ora = DateTimeFormatter.ofPattern("HH:mm");
            League l = new League(game.getJSONObject("league").getInt("id"));
            int id =game.getInt("id");

            Season stagione=new Season(game.getJSONObject("league").getInt("season"),game.getJSONObject("league").getInt("season"));
            Game g = new Game(game.getInt("id"),
                    l,stagione, LocalDate.parse(game.getString("date"), giorno),

                    LocalTime.parse(game.getString("time"), ora),
                    game.getString("timezone"),
                    game.getJSONObject("status").getString("long"),
                    null, null, game.getString("week"));
            Game response = gameRepository.save(g);
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * metodo per salvare un team se non esiste nel db
     * @param game oggetto json preso dalal risposta data da API sport
     * @param home se il team gioca in casa
     * @return {@link Team} salvato nel db o null
     */
    public Team salva_team(JSONObject game,int home){
        try{
            JSONObject team=new JSONObject();
            Team t = new Team();
            Team_season ts= new Team_season();
            Season s=  new Season(game.getJSONObject("league").getInt("season"));
            if(home==1){
                team=game.getJSONObject("teams").getJSONObject("home");
            }else{
                team=game.getJSONObject("teams").getJSONObject("away");
            }
            Team ris=teamRepository.GetTeamByIdTeam(team.getInt("id"));
            if(ris==null){
                t=new Team(team.getInt("id"),team.getString("name"),team.getString("logo"),false);
                teamRepository.save(t);


            }else{
                t=ris;
            }

            if(teamSeasonRepository.get_ts_completo(game.getJSONObject("league").getInt("id"),s.getId_season(), t.getId_team())==null){
                Team_season pezzo=  teamSeasonRepository.get_ts_date(game.getJSONObject("league").getInt("id"),s.getId_season());
                if(pezzo!=null) {
                    ts = new Team_season(new League(game.getJSONObject("league").getInt("id")),
                            s, t, pezzo.getStart_date(), pezzo.getEnd_date());
                    teamSeasonRepository.save(ts);
                }else{
                    JSONObject jason = jsonService.chiamata("https://v1.volleyball.api-sports.io/leagues?country=italy&season="+String.valueOf(s.getId_season()));
                    JSONArray leagues = jason.getJSONArray("response");
                    for(int i=0;i<leagues.length();i++){
                        JSONObject league=leagues.getJSONObject(i);
                        if(league.getInt("id")==game.getJSONObject("league").getInt("id")){

                            ts= new Team_season(new League(game.getJSONObject("league").getInt("id")),
                                    s,t,
                                    LocalDate.parse(league.getJSONArray("seasons").getJSONObject(0).getString("start")),
                                    LocalDate.parse(league.getJSONArray("seasons").getJSONObject(0).getString("end")));
                            teamSeasonRepository.save(ts);
                        }
                    }
                }
            }

            return t;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * metodo per salvare gli score di un game che esiste già nel db
     * @param game oggetto json preso dalal risposta data da API sport
     * @return {@link Score} salvato nel db o null
     */
    private List<Score> salva_score(JSONObject game) {
        try {
            Game g = gameRepository.GetGameByIdGame(game.getInt("id"));
            List<Score> lista = new ArrayList<>();
            for (int j = 0; j < game.getJSONObject("scores").length(); j++) {
                Score score = null;
                if (j == 0) {
                    Team t =salva_team(game,1);
                    if(teamRepository.GetTeamByIdTeam(t.getId_team())!=null) {
                        try {
                            score = new Score(g, t, true, game.getJSONObject("scores").getInt("home"));
                        } catch (Exception e) {
                            score = new Score(g, t, true, null);
                        }
                    }

                } else {
                    Team t =salva_team(game,0);
                    if(teamRepository.GetTeamByIdTeam(t.getId_team())!=null) {
                        try {
                            score = new Score(g, t, false, game.getJSONObject("scores").getInt("away"));
                        } catch (Exception e) {
                            score = new Score(g, t, false, null);
                        }
                    }

                }
                if (score!=null ) {
                    Score s = scoreRepository.save(score);
                    lista.add(s);
                }


            }
            return lista;


        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * metodo per salvare i periods degli scores che esistono già sul db, di un determinato game
     * @param scores {@link List<Score>} del game
     * @param game oggetto json preso dalal risposta data da API sport
     * @return lista di periods salvati nel db o un array vuoto
     */
    public List<Period> salva_periods(List<Score> scores, JSONObject game) {
        try {
            List<Period> lista = new ArrayList<>();
            if (!scores.isEmpty() && scores.size() == 2) {
                for (Score score : scores) {
                    if (score.isHome()) {
                        JSONObject periods = game.getJSONObject("periods");
                        for (int k = 1; k < periods.length() + 1; k++) {
                            Integer punti = 0;
                            try {
                                if (k == 1) {
                                    punti = periods.getJSONObject("first").getInt("home");
                                } else if (k == 2) {
                                    punti = periods.getJSONObject("second").getInt("home");
                                } else if (k == 3) {
                                    punti = periods.getJSONObject("third").getInt("home");
                                } else if (k == 4) {
                                    punti = periods.getJSONObject("fourth").getInt("home");
                                } else if (k == 5) {
                                    punti = periods.getJSONObject("fifth").getInt("home");
                                }
                            } catch (Exception e) {
                                punti = null;
                            }
                            if (punti != null) {
                                Period p = new Period(score, punti, String.valueOf(k));
                                periodRepository.save(p);
                                lista.add(p);
                            }
                        }
                    } else {
                        JSONObject periods = game.getJSONObject("periods");
                        for (int k = 1; k < periods.length() + 1; k++) {

                            Integer punti = 0;
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
                            } catch (Exception e) {
                                punti = null;
                            }
                            if (punti != null) {
                                Period p = new Period(score, punti, String.valueOf(k));
                                periodRepository.save(p);
                                lista.add(p);
                            }
                        }

                    }
                }


            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

}
