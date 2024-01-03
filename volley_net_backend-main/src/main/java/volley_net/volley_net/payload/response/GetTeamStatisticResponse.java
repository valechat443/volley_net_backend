package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTeamStatisticResponse {
    private int id_team;
    private String name;
    private String logo;
    private boolean national;
    private int played_home;
    private int played_away;
    private int wins_home;
    private int wins_away;
    private int losses_home;
    private int losses_away;
    private int draws_home;
    private int draws_away;
    private int for_goals_home;
    private int for_goals_away;
    private int against_goals_home;
    private int against_goals_away;

}
