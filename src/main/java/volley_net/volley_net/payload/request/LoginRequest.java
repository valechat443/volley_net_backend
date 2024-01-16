package volley_net.volley_net.payload.request;

import lombok.Getter;

@Getter
/**
 * richiesta per fare il login
 */
public class LoginRequest {
    /**
     * mail dell'utente
     */
    private String mail;
    /**
     * password dell'utente
     */
    private String password;
}
