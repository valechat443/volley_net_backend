package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * risposta con lista di gruppi di una lega di una stagione
 */
public class GetGroupResponse {
    /**
     * nome del {@link volley_net.volley_net.entity.Group}
     */
    private String group_name;
}
