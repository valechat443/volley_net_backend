package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;



@Getter
public class GameSpecificRequest {
    /**
     *
     * richiesta per singolo game
     * */
    @NotBlank
    private int id_game;
}
