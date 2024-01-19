package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
/**
 * richiesta di modifica del conto dell'utente
 */
public class UpdateMoneyRequest {


    /**
     * {@link volley_net.volley_net.service.TokenService} identificativo dell'utente
     */
    private String token;

    /**
     * valore da modificare (se positivo somma, se negativo sottraggo)
     */
    private int num;
}