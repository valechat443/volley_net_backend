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
 * risposta con l'esitod el login
 */
public class LoginResponse {
    /**
     * esito del login
     */
    private boolean conferma;
    /**
     * token identificativo dell'user, null in caso di fallimento nel login
     */
    private String token;
}
