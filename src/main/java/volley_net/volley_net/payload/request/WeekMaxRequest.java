package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class WeekMaxRequest {
    /**
     *numero della giornata massima di uan lega di una stagione
     *
     */


    private int season;


    private int id_league;
}

