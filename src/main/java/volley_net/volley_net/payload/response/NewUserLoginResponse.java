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
 * risposta con il token dell'utente
 */
public class NewUserLoginResponse {
    /**
     * token identificativo dell'utente
     */
    private String token;
}
