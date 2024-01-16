package volley_net.volley_net.payload.request;

import lombok.Getter;

@Getter
/**
 * richiesta di uan lista di team di una lega di una stagione
 */
public class SeasonIdLeague {
    /**
     * identificativo della stagione
     */
    private int season;
    /**
     * identificativo della lega
     */
    private int id_league;
}
