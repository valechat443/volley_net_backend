package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StandingRequest {
    /**
     *classifica di una lega di una stagione
     *
     */

    @NotBlank
    @Size(max = 4)
    private int season;

    @NotBlank
    private int id_league;

    @NotBlank
    @Size(max = 255)
    private String gruppo;
}
