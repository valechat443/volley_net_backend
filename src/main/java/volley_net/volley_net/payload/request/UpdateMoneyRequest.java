package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateMoneyRequest {

    /**
     *
     * somma o sottrazione "num"
     * */

    private String token;


    private int num;
}