package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class StandingRequest {
    /**
     *classifica di una lega di una stagione di un gruppo
     *
     */

    private int id_season;
    private int id_league;
    private int id_group;
}
