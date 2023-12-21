package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ListOfLeagueRequest {
    /**
     *lista di tutte le leghe di ua stagione
     *
     */
    @NotBlank @Size (max = 4)
    private int season;
}
