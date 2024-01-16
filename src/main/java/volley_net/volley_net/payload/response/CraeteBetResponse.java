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
 * risposta con l'esito del salvataggio di una scommessa
 */
public class CraeteBetResponse {
    /**
     * esito del salvataggio
     */
    private boolean create;
}
