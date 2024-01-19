package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
/**
 *
 * richiesta per un determinato utente
 * */
public class UserRequest {

    /**
     * {@link  volley_net.volley_net.service.TokenService} identificativo dell'utente
     */
    private String token;
}
