package volley_net.volley_net.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

@Service
@Slf4j
public class SheduleService {

    /**
     *controllo se un utente ha vinto una sua bet di un determinato game
     *
     */
    private ResponseEntity<?> check_bet() {
        return null;
    }

    /**
     *aggiorno le classifiche di tutte le leghe della stagione corrente
     *
     */
    private ResponseEntity<?> update_standings() {
        return null;
    }

    /**
     *aggiorna gli esiti e i punteggi di tutte le partite finite
     *
     */
    @Scheduled(cron = "0 3 16 * * ?")
    public void update_games() {
        try{
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-rapidapi-key", "d6f6c982d794f76eb4036f26b8387da0");
            headers.add("x-rapidapi-host", "v1.volleyball.api-sports.io");

            HttpEntity<String> entity = new HttpEntity<String>(headers);

            ResponseEntity<String> cose = restTemplate.exchange(
                    "https://v1.volleyball.api-sports.io/games?date="+LocalDate.now().toString(),
                    HttpMethod.GET,
                    entity,
                    String.class);

            JSONObject j = new JSONObject(cose.getBody());
            JSONArray response = j.getJSONArray("response");

            for (int i = 0; i < response.length(); i++) {
                JSONObject game = response.getJSONObject(i);
                if(game.getJSONObject("country").getString("code").equals("IT")){
                    log.info(game.toString());
                }
            }
        }catch (JSONException e) {
            throw new RuntimeException(e);
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
