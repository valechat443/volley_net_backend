package volley_net.volley_net.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import volley_net.volley_net.payload.request.GameGenericRequest;
import volley_net.volley_net.payload.request.GameSpecificRequest;
import volley_net.volley_net.payload.request.BetFutureRequest;
import volley_net.volley_net.payload.request.WeekMaxRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {


    /**
     *get partita da id partita
     *
     *
     */
    private ResponseEntity<?> get_game_specific(GameSpecificRequest request) {
        return null;
    }
    /**
     *lista di partite di una giornata (week) di una lega di una stagione
     *
     */
    private ResponseEntity<?> get_game_generic(GameGenericRequest request) {
        return null;
    }

    /**
     *get scommesse per i due giorni successivi a oggi
     *
     */
    private ResponseEntity<?> bets_page() {
        return null;
    }

    /**
     *scommesse di una giornata di una lega di una stagione
     *
     */
    private ResponseEntity<?> get_future_bets(BetFutureRequest request) {
        return null;
    }

    /**
     *ultimo game giocato di superlega
     *
     */
    private ResponseEntity<?> get_default_game() {
        return null;
    }

    /**
     *numero della giornata massima di uan lega di una stagione
     *
     */
    private ResponseEntity<?> get_week_max(WeekMaxRequest request) {
        return null;
    }

    /**
     *creo una bet di un certo utente che scommette su una certa squadra di una partita
     *
     */
    private ResponseEntity<?> create_bet() {
        return null;
    }
}
