package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class NewTeamListRequest {

    /**
     *
     * new team list
     * */
    @NotBlank
    private String token;

    @NotBlank
    private int id_team;

    @NotBlank
    private int season;

    @NotBlank
    private int id_league;
}