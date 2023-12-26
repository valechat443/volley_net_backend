package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetStandingResponse {
    private String team;
    private int position;
    private int points;
    private String form;
    private String zona;
}
