package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class BetFutureRequest {
    /**
     *scommesse di una giornata di una lega di una stagione
     *
     */

    private int week;


    private int season;


    private int id_league;
}