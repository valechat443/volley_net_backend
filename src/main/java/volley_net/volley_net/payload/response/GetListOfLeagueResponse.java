package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetListOfLeagueResponse {
    private int id_league;
    private String name;
    private String type;
    private String logo;
    private Date start_date;
    private Date end_date;

}
