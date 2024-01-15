package volley_net.volley_net.payload.request;

import lombok.Getter;

@Getter
/**
 * richiesta per chiedere di salvare un game
 */
public class SaveGameRequest {


    private int season;


    private int id_league;
}
