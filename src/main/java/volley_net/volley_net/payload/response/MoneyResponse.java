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
 * risposta con il bilancio dell'utente
 */
public class MoneyResponse {
    /**
     * bilancio dell'utente
     */
    private int money;
}
