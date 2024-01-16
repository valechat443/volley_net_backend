package volley_net.volley_net.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import volley_net.volley_net.entity.*;
import volley_net.volley_net.repository.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SheduleService {

    private final GameRepository gameRepository;

    private final JsonService jsonService;
    private final TeamSeasonRepository teamSeasonRepository;
    private  final StandingRepository standingRepository;
    private final GroupRepository groupRepository;


    /**
     *controllo se un utente ha vinto una sua bet di un determinato game
     *
     */
    private ResponseEntity<?> check_bet() {
        return null;
    }


    /**
     * aggiorno le classifiche di tutte le leghe della stagione corrente
     * @return messaggio con l'esito dell'operazione di salvataggio
     */
    public ResponseEntity<?> update_standings() {

        try{
            List<Team_season> leghe=teamSeasonRepository.getTeamSeasonFiniti(LocalDate.now());
            if(leghe.isEmpty()){
                return new ResponseEntity<>("nessuna lega trovata",HttpStatus.OK);
            }
            for (Team_season lega:leghe){
                if(standingRepository.getStandingFromTeamSeason(lega.getId_team_season()).isEmpty()){
                    String url="https://v1.volleyball.api-sports.io/standings?league="+String.valueOf(lega.getId_league().getId_league())+"&season="+String.valueOf(lega.getId_season().getId_season());
                    log.info(url);
                    JSONObject j = jsonService.chiamata(url);
                    JSONArray response = j.getJSONArray("response");
                    Thread.sleep(10000);
                    if(response.length()<1) {
                        log.info("Lunghezza response minore 1");
                    } else {
                        for (int i = 0; i < response.length(); i++) {
                            JSONArray gruppo=response.getJSONArray(i);

                            for (int k = 0; k < gruppo.length(); k++) {
                                JSONObject rank=gruppo.getJSONObject(k);
                                Group g =groupRepository.findByGroupName(rank.getJSONObject("group").getString("name"));
                                if(g==null){
                                    groupRepository.save(new Group((rank.getJSONObject("group").getString("name"))));
                                }
                                Standing s = new Standing(lega, g, rank.getInt("position"), rank.getInt("points"),
                                        rank.getString("form"), rank.getString("description"));
                                standingRepository.save(s);
                            }
                        }
                    }
                }
            }
            return new ResponseEntity<>("ok",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    /**
     *aggiorna gli esiti e i punteggi di tutte le partite finite
     * !! Da TESTARE !!
     */
    @Scheduled(cron = "0 40 15 * * ?")
    public void update_games() {
        try{

            JSONObject j = jsonService.chiamata("https://v1.volleyball.api-sports.io/games?date="+LocalDate.now().minusDays(1).toString());
            JSONArray response = j.getJSONArray("response");

            if(response.length()<1) {
                log.info("Lunghezza response minore 1");
            } else {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject game = response.getJSONObject(i);
                    if (game.getJSONObject("country").getString("code").equals("IT")) {
                        Thread.sleep(10000);
                        log.info(game.toString());

                        //es. input: "2024-01-08T16:00:00+00:00"
                        //Inizializzo la data senza il time
                        log.info("date");
                        String dateStr = game.getString("date").split("T")[0]; // Removes the time part
                        LocalDate date = LocalDate.parse(dateStr);
                        //Inizializzo il time
                        log.info("time");
                        String timeStr = game.getString("date").split("T")[1].split("\\+")[0];
                        LocalTime time = LocalTime.parse(timeStr);


                        //faccio un'altra chiamata (a odds)... finché non c'è un service che cerca oddsByIdGame... se ci sara
                        JSONObject jo = jsonService.chiamata("https://v1.volleyball.api-sports.io/odds?game=" + game.getInt("id"));
                        JSONArray responseJo = jo.getJSONArray("response");
                        log.info("chiamata odds");
                        Float oddsHome = 1.0f;
                        Float oddsAway = 1.0f;

                        log.info("Game: "+ game.toString());
                        log.info("Odds: "+ jo.toString());

                        if(responseJo.length()>0){
                            log.info("entro if odds");
                            JSONObject bookmaker = responseJo.getJSONObject(0).getJSONArray("bookmakers").getJSONObject(0);
                            log.info("bookmaker");
                            JSONObject bet = bookmaker.getJSONArray("bets").getJSONObject(0);
                            log.info("bet");
                            oddsHome = Float.parseFloat(bet.getJSONArray("values").getJSONObject(0).getString("odd"));
                            log.info("home");
                            oddsAway = Float.parseFloat(bet.getJSONArray("values").getJSONObject(1).getString("odd"));
                            log.info("away");
                        }

                        gameRepository.save(new Game(game.getInt("id"), new League(game.getJSONObject("league").getInt("id")), date, time, game.getString("timezone"), game.getJSONObject("status").getString("long"), oddsHome, oddsAway, game.getString("week")));

                    }
                }
            }
        }catch (Exception e) {
          log.info( e.getMessage());
        }
    }

    /**
     *aggiorno le statistiche di un team di una lega di una stagione
     *
     */
    private ResponseEntity<?> get_team_statistic() {
        return null;
    }


}
