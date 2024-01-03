package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamsBetPage {
    private int id_team;
    private String name;
    private String logo;
    private boolean national;
    private boolean home;
    private Integer set;
    private Float odd;
}
