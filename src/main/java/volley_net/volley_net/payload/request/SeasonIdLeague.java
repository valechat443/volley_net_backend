package volley_net.volley_net.payload.request;

import lombok.Getter;

@Getter
/**
 * richiesta di uan lista di team di una lega di una stagione
 */
public class SeasonIdLeague {
    /**
     * identificativo della {@link volley_net.volley_net.entity.Season}
     */
    private int season;
    /**
     * identificativo della {@link volley_net.volley_net.entity.League}
     */
    private int id_league;
}
