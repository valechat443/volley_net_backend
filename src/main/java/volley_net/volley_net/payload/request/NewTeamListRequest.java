package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
/**
 * richiesta per creare un nuovo team_list
 */
public class NewTeamListRequest {

    /**
     *{@link  volley_net.volley_net.service.TokenService} identificativo dell'utente
     *
     * */
    private String token;

    /**
     * identificativo del {@link volley_net.volley_net.entity.Team}
     */
    private int id_team;

    /**
     * identificativo della {@link volley_net.volley_net.entity.Season}
     */
    private int season;

    /**
     * identificativo della {@link volley_net.volley_net.entity.League}
     */
    private int id_league;
}