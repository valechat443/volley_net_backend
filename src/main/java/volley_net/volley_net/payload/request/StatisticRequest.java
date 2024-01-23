package volley_net.volley_net.payload.request;

import lombok.Getter;

@Getter
/**
 * richiesta per le statistiche del team
 */
public class StatisticRequest {
    /**
     * identificativo di {@link volley_net.volley_net.entity.Team}
     */
    private int id_team;
    /**
     * identificativo di {@link volley_net.volley_net.entity.Season}
     */
    private int id_season;
    /**
     * identificativo di {@link volley_net.volley_net.entity.League}
     */
    private int id_lega;
}