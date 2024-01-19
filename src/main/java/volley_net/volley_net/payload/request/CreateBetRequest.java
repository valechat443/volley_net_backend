package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
/**
 *richiesta per la creazione di una bet di un certo utente che scommette su una certa squadra di una partita
 *
 */
public class CreateBetRequest {

    /**
     * {@link  volley_net.volley_net.service.TokenService} che identifica l'utente
     */
    private String token;

    /**
     * {@link volley_net.volley_net.entity.Team} su cui l'utente scommette
     */
    private int id_team;

    /**
     * {@link volley_net.volley_net.entity.Game} su cui l'utente scommette
     */
    private int id_game;
}
