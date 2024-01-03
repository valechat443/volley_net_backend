package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class GetRankRequest {
    @NotBlank
    private String token;
}
