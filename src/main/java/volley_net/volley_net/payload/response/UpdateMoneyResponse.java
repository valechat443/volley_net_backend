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
/**
 * bilancio aggiornato dell'utente
 */
public class UpdateMoneyResponse {
    /**
     * bilancio aggiornato
     */
    private int money;
}