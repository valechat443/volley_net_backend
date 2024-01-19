package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
/**
 *richiesta per una lista di partite di una giornata (week) di una lega di una stagione
 *
 */
public class GameGenericRequest {

    /**
     * settimana in cui le partite si giocano
     */
    private String week;




    /**
     * {@link volley_net.volley_net.entity.Season} in cui le partite si giocano
     */
    private int season;

    /**
     * {@link volley_net.volley_net.entity.League} in cui le partite si giocano
     */
    private int id_league;


    /**
     * costruttore
     * @param week giornata in cui le partite si svolgono
     * @param season {@link volley_net.volley_net.entity.Season} in cui le partite si svolgono
     * @param id_league {@link volley_net.volley_net.entity.League} in cui le partite si svolgono
     */

    public GameGenericRequest(String week, int season, int id_league) {
        this.week = week;
        this.season = season;
        this.id_league = id_league;
    }
}