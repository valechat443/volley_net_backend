package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * risposta con una lista di scommesse fate dall'utente
 */
public class BetUserResponse {

    /**
     * identificativo della partita
     */
    private int id_game;
    /**
     * logo della squadra su cui il giocatore ha scommesso
     */
    private String logo_scommesso;
    /**
     * logo della squadra su cui il giocatore NON ha scommesso
     */
    private String logo_altro;
    /**
     * nome della squadra su cui il giocatore ha scommesso
     */
    private String name_scommesso;
    /**
     * nome della squadra su cui il giocatore NON ha scommesso
     */
    private String name_altro;

}
