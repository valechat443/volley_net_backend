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
 * risposta con i dati di un singolo team
 */
public class GetTeamResponse {
    /**
     * identificativo del {@link volley_net.volley_net.entity.Team}
     */
    private int id_team;
    /**
     * nome del team
     */
    private String name;
    /**
     * link al logo del team
     */
    private String logo;
    /**
     * se il team è nazionale
     */
    private boolean national;
}
