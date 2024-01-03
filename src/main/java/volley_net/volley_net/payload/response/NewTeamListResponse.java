package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import volley_net.volley_net.entity.Team_list;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewTeamListResponse {
    private Team_list team_list;
}