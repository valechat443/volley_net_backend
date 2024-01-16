package volley_net.volley_net.payload.request;

import lombok.Getter;

@Getter
/**
 * richiesta per le statistiche del team
 */
public class StatisticRequest {
    /**
     * identificativo di team
     */
    private int id_team;
    /**
     * identificativo di stagione
     */
    private int id_season;
    /**
     * identificativo di lega
     */
    private int id_lega;
}