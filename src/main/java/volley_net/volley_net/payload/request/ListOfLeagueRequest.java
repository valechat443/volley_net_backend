package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
@Getter
public class ListOfLeagueRequest {
    /**
     *lista di tutte le leghe di una stagione
     *
     */
    private int season;

}
