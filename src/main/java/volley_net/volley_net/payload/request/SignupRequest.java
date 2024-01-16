package volley_net.volley_net.payload.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
/**
 * richiesta per la registrazione
 *
 * */
public class SignupRequest {

    /**
     * mail dell'utente
     */
    private String mail;

    /**
     * password del'utente
     */
    private String password;

    /**
     * username dell'utente
     */
    private String username;

    /**
     * se l'utente è un admin
     */
    private boolean admin;
}

