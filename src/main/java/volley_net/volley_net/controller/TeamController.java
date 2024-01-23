package volley_net.volley_net.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volley_net.volley_net.entity.Statistic;
import volley_net.volley_net.payload.request.GetTeamRequest;
import volley_net.volley_net.payload.request.SaveStatisticRequest;
import volley_net.volley_net.payload.request.SeasonIdLeague;
import volley_net.volley_net.payload.request.StatisticRequest;
import volley_net.volley_net.service.SheduleService;
import volley_net.volley_net.service.TeamService;
import java.util.*;
@RestController
@RequestMapping("team")
@RequiredArgsConstructor
@CrossOrigin
/**
 * controller di team
 */
public class TeamController {
    /**
     *servizi relativi a team {@link TeamService}
     */
    private  final TeamService teamService;
    /**
     * servizi relativi a scheduling {@link SheduleService}
     */
    private final SheduleService sheduleService;

    /**
     * controller per avere i dati di un team specifico
     * @param request {@link GetTeamRequest}
     * @return dati relativi a un determinato {@link volley_net.volley_net.entity.Team}
     */
    @PostMapping("/getTeam")
    public ResponseEntity<?> get_team(@RequestBody @Valid GetTeamRequest request){
        return teamService.get_team(request);
    }

    /**
     * controller per avere una lista di team di una lega in una determinata stagione
     * @param request {@link SeasonIdLeague}
     * @return lista di team {@link List<volley_net.volley_net.entity.Team>} di una lega in una determinata stagione
     */
    @PostMapping("/getList")
    public ResponseEntity<?> get_list_of_team(@RequestBody @Valid SeasonIdLeague request){
        return teamService.get_list_of_team(request);
    }

    /**
     * controller per avere le statistiche di uno specifico team
     * @param request {@link  StatisticRequest}
     * @return Lista di {@link  volley_net.volley_net.payload.response.GetTeamStatisticResponse} con le statistiche di un team
     */
    @PostMapping("/statistics")
    public ResponseEntity<?> getStatistics(@RequestBody @Valid StatisticRequest request) {
        return teamService.getStatistic(request);
    }



    /**
     * controller di test per salvare le statistiche di un team
     * @param request
     * @return messaggio sull'esito del salvataggio delle statistiche
     */
    @PostMapping("/saveStat")
    public ResponseEntity<?> saveStatistics(@RequestBody @Valid SaveStatisticRequest request) {
        return teamService.salva_statistic(request);
    }

    /**
     * controller di test per salvare una classifica
     * @return messaggio con l'esito dell'operazione di salvataggio
     */
    @GetMapping("/saveStanding")
    public ResponseEntity<?> saveStanding() {
        return sheduleService.update_standings();
    }
}
