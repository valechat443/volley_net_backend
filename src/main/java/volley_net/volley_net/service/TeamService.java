package volley_net.volley_net.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import volley_net.volley_net.entity.User;
import volley_net.volley_net.payload.request.SignupRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamService {

    /**
     *statistiche di un singolo team
     *
     */
    private ResponseEntity<?> get_team_statistics() {
        return null;
    }

    /**
    *lista di team di una determinata lega di una determinata stagione
    *
     */
    private ResponseEntity<?> get_team_list() {
        return null;
    }
    /**
     *get singolo team
     *
     */
    private ResponseEntity<?> get_team() {
        return null;
    }
}
