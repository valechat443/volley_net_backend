package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
/**
 *richiesta per la classifica di un gruppo, di una lega, di una stagione
 *
 */
public class StandingRequest {

    /**
     * identificativo della {@link volley_net.volley_net.entity.Season}
     */
    private int id_season;
    /**
     * identificativo della {@link  volley_net.volley_net.entity.League}
     */
    private int id_league;
    /**
     * identificativo del {@link volley_net.volley_net.entity.Group}
     */
    private int id_group;
}
