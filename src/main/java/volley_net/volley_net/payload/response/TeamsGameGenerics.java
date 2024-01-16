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
 * lista dui team di una partita
 */
public class TeamsGameGenerics {
    /**
     * identificativo del team
     */
    private int id_team;
    /**
     * nome del tema
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
    /**
     * se il team giocava in casa
     */
    private boolean home;
    /**
     * numero di set vinti dal team
     */
    private int set;
}
