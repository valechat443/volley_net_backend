package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
/**
 * richiesta per creare un nuovo team_list
 */
public class NewTeamListRequest {

    /**
     *token identificativo dell'utente
     *
     * */
    private String token;

    /**
     * identificativo del team
     */
    private int id_team;

    /**
     * identificativo della stagione
     */
    private int season;

    /**
     * identificativo della lega
     */
    private int id_league;
}