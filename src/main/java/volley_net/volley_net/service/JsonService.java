package volley_net.volley_net.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import volley_net.volley_net.payload.request.SaveGameRequest;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class JsonService {


    /**
     * metodo per effettuare richieste ad API sport
     * @param url endpoint di API sport
     * @return oggetto jason con dentro una lista di game
     */
    public JSONObject chiamata(String url) {


        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("x-rapidapi-key", "d6f6c982d794f76eb4036f26b8387da0");
        headers.add("x-rapidapi-host", "v1.volleyball.api-sports.io");

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> cose = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class);
        try {
            return new JSONObject(cose.getBody());
        } catch (Exception e) {
            return null;
        }
    }

}
