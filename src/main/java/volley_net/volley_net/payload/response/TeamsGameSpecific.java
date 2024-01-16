package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * lista di team di un game specifico
 */
public class TeamsGameSpecific {
    /**
     * identificativo del team
     */
    private int id_team;
    /**
     * nome del team
     */
    private String name;
    /**
     * link al logo del team
     *
     */
    private String logo;
    /**
     * se il team è nazionale
     */
    private boolean national;
    /**
     * se il team giocava in casa
     */
    private boolean home;

    /**
     * numero di set vinti da team
     */
    private Integer set;
    /**
     * lista dei punteggi fatti dal team nella partita
     */

    private List<Integer> periods;
}
