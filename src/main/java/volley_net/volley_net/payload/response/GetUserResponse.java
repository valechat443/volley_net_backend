package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {
    private int id_user;
    private String mail;
    private String username;
    private boolean verified;
    private boolean admin;
    private int money;
    private int count_bet;
}
