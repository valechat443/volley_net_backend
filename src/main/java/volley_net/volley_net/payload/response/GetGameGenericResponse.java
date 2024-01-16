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
 * richiesta di una lista di partite di una lega di una stagione
 */
public class GetGameGenericResponse {
    /**
     * identificativo della partita
     */
    private int id_game;
    /**
     * data a cui si è svolto/si svolgerà la partita
     */
    private LocalDate data;
    /**
     * ora a cui si è svolto/si svolgerà la partita
     */
    private LocalTime ora;
    /**
     * status della partita (not started, finished, ecc...)
     */
    private String status;

    /**
     * giornata in cui si giocherà la partita
     */
    private String week;
    /**
     * nome della lega
     */
    private String league_name;
    /**
     * team che hanno giocato/giocheranno nella partita
     */
    private List<TeamsGameGenerics> teams;


}
