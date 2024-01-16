package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
/**
 * richiesta di lista di tutte le leghe di una stagione
 */
public class ListOfLeagueRequest {
    /**
     *identificativo della stagione
     *
     */
    private int season;
}
