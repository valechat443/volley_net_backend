package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
/**
 * richiesta dalla giornata massima, contiene il numero della stagione e della lega richiesta
 */
public class WeekMaxRequest {



    private int season;


    private int id_league;
}

