package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GameGenericRequest {
    /**
     *
     * richiesta ultimo game week superlega
     * */
    @NotBlank
    @Size(max = 2)
    private int week;

    @NotBlank
    @Size(max = 4)
    private int season;

    @NotBlank
    private int id_league;
}