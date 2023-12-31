package volley_net.volley_net.payload.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequest {
    /**
     *
     * richiesta per la registrazione
     * */

    private String mail;


    private String password;


    private String username;


    private boolean admin;
}

