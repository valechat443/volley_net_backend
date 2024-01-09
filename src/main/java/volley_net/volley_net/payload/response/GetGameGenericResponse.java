package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetGameGenericResponse {
    private int id_game;
    private LocalDate data;
    private LocalTime ora;
    private String status;
    private String week;
    private String league_name;
    private List<TeamsGameGenerics> teams;

}
