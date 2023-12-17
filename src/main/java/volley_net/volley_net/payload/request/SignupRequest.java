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
    @NotBlank @Email
    private String mail;

    @NotBlank @Size(min = 6)
    private String password;

    @NotBlank @Size(min = 4,max = 255)
    private String username;

    @NotBlank
    private boolean admin;








}

