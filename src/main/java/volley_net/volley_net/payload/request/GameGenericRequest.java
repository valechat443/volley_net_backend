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
     * stagione in cui le partite si giocano
     */
    private int season;

    /**
     * lega in cui le partite si giocano
     */
    private int id_league;

    /**
     * costruttore
     * @param week
     * @param season
     * @param id_league
     */
    public GameGenericRequest(String week, int season, int id_league) {
        this.week = week;
        this.season = season;
        this.id_league = id_league;
    }
}