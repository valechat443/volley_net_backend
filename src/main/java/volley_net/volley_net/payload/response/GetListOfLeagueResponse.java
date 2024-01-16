package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * risposta con lista di leghe di una stagione
 */
public class GetListOfLeagueResponse {
    /**
     * identificativo leghe
     */
    private int id_league;
    /**
     * nome della lega
     */
    private String name;
    /**
     * tipo della lega
     */
    private String type;
    /**
     * logo della lega
     */
    private String logo;
    /**
     * data d'inizio della lega
     */
    private LocalDate start_date;
    /**
     * data di fine della lega
     */
    private LocalDate end_date;

}
