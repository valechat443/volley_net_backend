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
    @NotBlank
    private String token;

    @NotNull
    private int num;
}