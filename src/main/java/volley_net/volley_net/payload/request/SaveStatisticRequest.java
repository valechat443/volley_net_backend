package volley_net.volley_net.payload.request;

import lombok.Getter;

@Getter
/**
 * richiesta epr salvare nuove statistiche di un team
 */
public class SaveStatisticRequest {

    /**
     * identificativo di una {@link  volley_net.volley_net.entity.League}
     */
    private int id_league;
    /**
     * identificativo di una {@link volley_net.volley_net.entity.Season}
     */
    private int id_season;
    /**
     * identificatore di un {@link volley_net.volley_net.entity.Team}
     */
    private int id_team;
}
