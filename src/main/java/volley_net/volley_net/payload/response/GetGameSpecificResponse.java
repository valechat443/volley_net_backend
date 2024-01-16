package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * risposta con dati di una partita specifica
 */
public class GetGameSpecificResponse {
    /**
     * identificativo dalla partita
     */
    private int id_game;
    /**
     * data a cui si è svolto/si svolgerà la partita
     */
    private LocalDate date;
    /**
     * ora a cui si è svolto/si svolgerà la partita
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
     * team che hanno giocato/giocheranno nella partita
     */

    private List<TeamsGameSpecific> teams;

}
