package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BetUserResponse {

    private int id_game;
    private String logo_scommesso;
    private String logo_altro;
    private String name_scommesso;
    private String name_altro;

}
