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
 * risposta con le statistiche di un team in un gruppo, di una lega, in una stagione
 */
public class GetTeamStatisticResponse {
    /**
     * identificativo del team
     */
    private int id_team;
    /**
     * nome del team
     */
    private String name;
    /**
     * logo del team
     */
    private String logo;
    /**
     * se il team è nazionale
     */
    private boolean national;
    /**
     * numero di partite giocate in casa
     */
    private int played_home;
    /**
     * numero di partite giocate fuori casa
     */
    private int played_away;
    /**
     * numero di partite vinte in casa
     */
    private int wins_home;
    /**
     * numero di partite vinte fuori casa
     */
    private int wins_away;
    /**
     * numero di partite perse in casa
     */
    private int losses_home;
    /**
     * numero di partite perse fuori casa
     */
    private int losses_away;
    /**
     * numero di partite pareggiate in casa
     */
    private int draws_home;
    /**
     * numero di partite pareggiate fuori casa
     */
    private int draws_away;
    /**
     * numero di goals fatti in casa
     */
    private int for_goals_home;
    /**
     * numero di goals fatti fuori casa
     */
    private int for_goals_away;
    /**
     * numero di goals subiti in casa
     */
    private int against_goals_home;
    /**
     * numero di goals subiti fuori casa
     */
    private int against_goals_away;

}
