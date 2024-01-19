package volley_net.volley_net.payload.request;

import lombok.Getter;

@Getter
/**
 * richiesta per chiedere di salvare un game
 */

public class SaveGameRequest {

    /**
     * identificativo della {@link volley_net.volley_net.entity.Season}
     */
    private int season;

    /**
     * identificativo della {@link volley_net.volley_net.entity.League}
     */
    private int id_league;
}
