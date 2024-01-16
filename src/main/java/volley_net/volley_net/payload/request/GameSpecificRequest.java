package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;



@Getter
/**
 *richiesta di una partita specifica
 *
 *
 */
public class GameSpecificRequest {

    /**
     * codice identificativo della partita
     */
    private int id_game;
}
