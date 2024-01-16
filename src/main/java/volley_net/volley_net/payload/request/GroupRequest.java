package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
/**
 *richiesta dei gruppi di una lega di una stagione
 *
 */
public class GroupRequest {

    /**
     *identificativo della stagione
     */
    private int season;
    /**
     * identificativo della lega
     */
    private int id_league;
    public GroupRequest(int season, int id_league) {
        this.season = season;
        this.id_league = id_league;
    }
}
