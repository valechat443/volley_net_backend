package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class NewTeamListRequest {

    /**
     *
     * new team list
     * */

    private String token;


    private int id_team;


    private int season;


    private int id_league;
}