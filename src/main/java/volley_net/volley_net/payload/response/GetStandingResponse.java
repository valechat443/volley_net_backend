package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * classifica di un gruppo, di una lega, di una stagione
 */
public class GetStandingResponse {
    /**
     * nome del team
     */
    private String team;
    /**
     * posizione in classifica del team
     */
    private int position;
    /**
     * punti che il team ha fatto
     */
    private int points;
    /**
     * serie di vittorie/sconfitte del team
     */
    private String form;
    /**
     * zone di esclusione in cui si trova il team
     */
    private String zona;
    /**
     *  link al logo del team
     */
    private String logo;
}
