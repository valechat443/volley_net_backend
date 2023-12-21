package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateBetRequest {
    /**
     *creo una bet di un certo utente che scommette su una certa squadra di una partita
     *
     */
    @NotBlank
    private String token;

    @NotBlank
    private int id_team;

    @NotBlank
    private int id_game;
}
