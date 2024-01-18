package volley_net.volley_net.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volley_net.volley_net.entity.Group;
import volley_net.volley_net.entity.League;
import volley_net.volley_net.entity.Standing;
import volley_net.volley_net.payload.request.GroupRequest;
import volley_net.volley_net.payload.request.ListOfLeagueRequest;
import volley_net.volley_net.payload.request.StandingRequest;
import volley_net.volley_net.payload.response.GetListOfLeagueResponse;
import volley_net.volley_net.payload.response.GetStandingResponse;
import volley_net.volley_net.repository.GroupRepository;
import volley_net.volley_net.service.LeagueService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("league")
@CrossOrigin
/**
 * controller di league
 */
public class LeagueController {
    /**
     * servizi relativi a league {@link LeagueService}
     */
    private final LeagueService leagueService;
    @Autowired
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    /**
     * controller avere la lista di leghe di una stagione
     * @param request {@link ListOfLeagueRequest}
     * @return lista di {@link  GetListOfLeagueResponse} o una lista vuota
     */
    @PostMapping("/leagues")
    public ResponseEntity<?> get_list_of_league(@RequestBody @Valid ListOfLeagueRequest request){

        return leagueService.getLeaguesFromSeason(request.getSeason());
    }

    /**
     * controller per avere la classifica di un gruppo, di una lega, di uan stagione
     * @param request {@link  StandingRequest}
     * @return lista di {@link GetStandingResponse} o una lista vuota
     */
    @PostMapping ("/standings")
    public ResponseEntity<?> getTeamsOfLeagueAndSeason(@RequestBody @Valid StandingRequest request) {

        return leagueService.findStandingBySeasonLeagueAndGroup(request);
    }

    /**
     * operazioni sul db relative a group {@link GroupRepository}
     */
    @Autowired
    private GroupRepository groupRepository;

    /**
     * controller per avere uan lista di tutti i gruppi di ua lega di una stagione
     * @param request {@link GroupRequest}
     * @return lista si stringhe contenente i nomi dei gruppi o una lista vuota
     */
    @PostMapping ("/groups")
    public ResponseEntity<?> getGroups(@RequestBody GroupRequest request) {
        return leagueService.getGroup(request);
    }
}