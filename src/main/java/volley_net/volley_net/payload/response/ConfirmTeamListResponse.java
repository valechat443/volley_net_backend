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
 * messaggio con l'esito della creazione di una nuova team_list
 */
public class ConfirmTeamListResponse {
    /**
     * esito del salvataggio
     */
    private boolean confirm;
}
