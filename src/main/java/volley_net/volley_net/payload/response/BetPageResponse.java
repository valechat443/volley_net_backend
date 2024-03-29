package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * risposta per la pagina dove l'utente può scommettere
 */
public class BetPageResponse {
    /**
     * identificativo della {@link volley_net.volley_net.entity.Game}
     */
    private int id_game;
    /**
     * data in cui si svolgerà la partita
     */
    private LocalDate date;
    /**
     * ora in cui si terrà la partita
     */
    private LocalTime time;
    /**
     * status della partita (not started, finished, ecc...)
     */
    private String status;

    /**
     * giornata in cui si giocherà la partita
     */
    private String week;

    /**
     * nome della lega in cui si giocherà la partita
     */
    private String league_name;
    /**
     * lista dei team {@link TeamsBetPage} che si scontrano nella partita
     */
    private List<TeamsBetPage> teams;

}
