package volley_net.volley_net.service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
/**
 * token che identifica l'utente
 */
public class UserToken {
    /**
     * identificativo di {@link volley_net.volley_net.entity.User}
     */
    private int id_token;
}
