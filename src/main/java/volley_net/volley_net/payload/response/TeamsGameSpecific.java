package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamsGameSpecific {
    private int id_team;
    private String name;
    private String logo;
    private boolean national;
    private boolean home;
    private Integer set;
    private List<Integer> periods;
}
