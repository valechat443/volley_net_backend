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
 * lista di team in una scommessa
 */
public class TeamsBetPage {
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
     * set vinti dal team
     */
    private Integer set;
    /**
     * possibilità del team
     */
    private Float odd;
}
