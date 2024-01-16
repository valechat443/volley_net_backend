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
 * risposta con la classifica degli utenti in ordine di count_bet
 */
public class ListUserRankResponse {
    /**
     * identificaativo dell'utente
     */
    private int id_user;
    /**
     * username dell'utente
     */
    private String username;
    /**
     * numero di scommesse che l'utente ha vinto
     */
    private int count_bet;
}
