package volley_net.volley_net.payload.request;

import lombok.Getter;

@Getter
/**
 * richiesta per avere un team specifico
 */
public class GetTeamRequest {

    /**
     * identificativo del {@link volley_net.volley_net.entity.Team}
     */
    private int id_team;
}

