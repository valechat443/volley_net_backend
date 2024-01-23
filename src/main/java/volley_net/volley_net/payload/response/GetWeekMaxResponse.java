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
 * risposta con la settimana massima che una lega può avere
 */
public class GetWeekMaxResponse {

    /**
     * settimana massima
     */

    private String max;
}
