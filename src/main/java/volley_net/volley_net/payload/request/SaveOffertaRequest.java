package volley_net.volley_net.payload.request;

import lombok.Getter;

@Getter

/**
 * richiesta per salvare una offerta acquistata da un utente
 */
public class SaveOffertaRequest {

    /**
     * {@link volley_net.volley_net.service.TokenService} identificativo dell'utente
     */
    private String token;
    /**
     * descrizione dell'offerta
     */

    private String nome_offerta;
}
