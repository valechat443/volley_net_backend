package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import volley_net.volley_net.entity.Team_list;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * risposta con l'esito della creazione di un nuovo team_list
 */
public class NewTeamListResponse {
    /**
     * esito della creazione
     */
    private boolean conferma;
}