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
     * token che identifica l'utente
     */
    private String token;

    /**
     * team su cui l'utente scommette
     */
    private int id_team;

    /**
     * partita su cui l'utente scomm ette
     */
    private int id_game;
}
