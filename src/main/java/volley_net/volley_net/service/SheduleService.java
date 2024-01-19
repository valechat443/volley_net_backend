package volley_net.volley_net.service;

import jakarta.persistence.criteria.CriteriaBuilder;
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
import volley_net.volley_net.payload.response.CheckBetResponse;
import volley_net.volley_net.repository.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SheduleService {

    private final GameRepository gameRepository;
    private final BetRepository betRepository;
    private final JsonService jsonService;
    private final TeamSeasonRepository teamSeasonRepository;
    private  final StandingRepository standingRepository;
    private final GroupRepository groupRepository;

    private final TokenService tokenService;
    private final UserRepository userRepository;


    /**
     *controllo segli utenti hanno vinto le partite dei game di ieri
     * @Return Lista bet vincenti e perdenti (id_bet, token, true/false(vittoria));
     */
    @Scheduled(cron = "0 0 24 * * ?")
    public ResponseEntity<?> check_bet() {
        List<String> odds = gameRepository.getOddsWinnerGame(LocalDate.of(2022,10,9));
        List<CheckBetResponse> response =new ArrayList<>();

        if(!odds.isEmpty()) {
            String[] s;
            List<Bet> bets;
            Float n;

            for (String odd : odds) {
                s = odd.split(","); //"id_game,id_team,home(bool),home_odds,away_odds"
                bets = betRepository.ListOfBetsGame(Integer.parseInt(s[0]));

                if(!bets.isEmpty()){
                    for (Bet bet : bets) {
                        if (bet.getId_team().getId_team() == Integer.parseInt(s[1])) {
                            n = 0f;

                            if(s[3]!="null" && s[4]!="null"){
                                if (s[2] == "true") { //if "home" == true multiplie for home_odds(s[3])
                                    n = 50 * Float.parseFloat(s[3]);
                                } else { //multiplie for away_odds(s[4])
                                    n = 50 * Float.parseFloat(s[4]);
                                }
                            }

                            userRepository.updateMoney(n.intValue()-50, bet.getId_user().getId_user());
                            userRepository.updateCountBet(bet.getId_user().getId_user());
                            response.add(new CheckBetResponse(bet.getId_bet(),tokenService.createToken(bet.getId_user().getId_user()),true)); //
                        } else {
                            response.add(new CheckBetResponse(bet.getId_bet(),tokenService.createToken(bet.getId_user().getId_user()),false));
                        }
                    }
                }
            }
        } else {
            return new ResponseEntity<>("Nessun game trovato", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
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
                                    g=groupRepository.save(new Group((rank.getJSONObject("group").getString("name"))));
                                }
                                Team_season ts= teamSeasonRepository.get_ts_completo(lega.getId_league().getId_league(),lega.getId_season().getYear(),rank.getJSONObject("team").getInt("id"));
                                Standing s = new Standing(ts, g, rank.getInt("position"), rank.getInt("points"),
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
     *aggiorna gli esiti e i punteggi di tutte i {@link Game} finiti
     *
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


                        //facio un'altra chiamata (a odds)... finché non c'è un service che cerca oddsByIdGame... se ci sara
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


                        gameRepository.save(new Game(game.getInt("id"), new League(game.getJSONObject("league").getInt("id")),new Season(game.getJSONObject("league").getInt("season"),game.getJSONObject("league").getInt("season")), date, time, game.getString("timezone"), game.getJSONObject("status").getString("long"), oddsHome, oddsAway, game.getString("week")));


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
