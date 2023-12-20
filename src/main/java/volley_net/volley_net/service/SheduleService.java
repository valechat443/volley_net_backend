package volley_net.volley_net.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    private ResponseEntity<?> update_games() {
        return null;
    }

    /**
     *aggiorno le statistiche di un team di una lega di una stagione
     *
     */
    private ResponseEntity<?> get_team_statistic() {
        return null;
    }


}
