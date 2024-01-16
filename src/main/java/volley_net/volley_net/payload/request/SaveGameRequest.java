package volley_net.volley_net.payload.request;

import lombok.Getter;

@Getter
/**
 * richiesta per chiedere di salvare un game
 */

public class SaveGameRequest {

    /**
     * identificativo della stagione
     */
    private int season;

    /**
     * identificativo della lega
     */
    private int id_league;
}
