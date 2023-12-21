package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateBetRequest {
    @NotBlank
    private String token;

    @NotBlank
    private int id_team;

    @NotBlank
    private int id_game;
}
