package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
/**
 * richiesta di tutte le scommesse di una giornata di una lega di una stagione
 */
public class BetFutureRequest {

    /**
     * giornata in cui le partite si giocheranno
     */
    private String week;

    /**
     * stagione in cui le partite si giocheranno
     */
    private int season;

    /**
     * legha in cui le partite si giocheranno
     */
    private int id_league;
}