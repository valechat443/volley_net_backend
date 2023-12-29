package volley_net.volley_net.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class GameGenericRequest {
    /**
     *lista di partite di una giornata (week) di una lega di una stagione
     *
     */

    private int week;



    private int season;


    private int id_league;

    public GameGenericRequest(int week, int season, int id_league) {
        this.week = week;
        this.season = season;
        this.id_league = id_league;
    }
}