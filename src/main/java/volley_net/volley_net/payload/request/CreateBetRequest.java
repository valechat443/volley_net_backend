package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateBetRequest {
    /**
     *creo una bet di un certo utente che scommette su una certa squadra di una partita
     *
     */

    private String token;


    private int id_team;


    private int id_game;
}
