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
 * risposta con l'esito del login
 */
public class LoginResponse {
    /**
     * esito del login
     */
    private boolean conferma;
    /**
     * {@link volley_net.volley_net.service.TokenService} identificativo dell'user, null in caso di fallimento nel login
     */
    private String token;
}
