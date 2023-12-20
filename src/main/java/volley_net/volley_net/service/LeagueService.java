package volley_net.volley_net.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeagueService {

    /**
     *lista di tutte le leghe di ua stagione
     *
     */
    private ResponseEntity<?> get_list_of_league() {
        return null;
    }
    /**
     *classifica di una lega di una stagione
     *
     */
    private ResponseEntity<?> get_standings() {
        return null;
    }

    /**
     *gruppi di una lega di una stagione
     *
     */
    private ResponseEntity<?> get_groups() {
        return null;
    }



}
