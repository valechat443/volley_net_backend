package volley_net.volley_net.payload.request;

import lombok.Getter;

@Getter
/**
 * richiesta epr salvare nuove statistiche di un team
 */
public class SaveStatisticRequest {

    /**
     * identificativo di una lega
     */
    private int id_league;
    /**
     * identificativo di una stagione
     */
    private int id_season;
    /**
     * identificatore di un team
     */

    private int id_team;
}
