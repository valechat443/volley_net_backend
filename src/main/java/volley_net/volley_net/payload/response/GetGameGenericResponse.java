package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetGameGenericResponse {
    private int id_game;
    private Date date;
    private Time time;
    private String status;
    private int week;
    private List<TeamsGameGenerics> teams;

}
