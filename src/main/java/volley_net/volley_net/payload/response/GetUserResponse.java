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
 * risposta con i dati dell'user
 */
public class GetUserResponse {
    /**
     * identificativo dell'user
     */
    private int id_user;
    /**
     * mail dell'utente
     */
    private String mail;
    /**
     * username dell'utente
     */
    private String username;
    /**
     * se l'utente è verificato
     */
    private boolean verified;
    /**
     * se l'utente è un admin
     */
    private boolean admin;
    /**
     * valuta che l'utente ha
     */
    private int money;
    /**
     * numero di scommesse che l'utente ha indovinato
     */
    private int count_bet;
}
