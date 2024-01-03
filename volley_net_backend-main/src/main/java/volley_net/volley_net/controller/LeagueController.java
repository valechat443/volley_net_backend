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
import volley_net.volley_net.payload.response.GetStandingResponse;
import volley_net.volley_net.repository.GroupRepository;
import volley_net.volley_net.service.LeagueService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("league")
@CrossOrigin
public class LeagueController {
    private final LeagueService leagueService;
    @Autowired
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping("/leagues")
    public ResponseEntity<?> get_list_of_league(@RequestBody @Valid ListOfLeagueRequest request){
        List<League> leagues = leagueService.getLeaguesFromSeason(request.getSeason());
        return ResponseEntity.ok(leagues);
    }
    @GetMapping("/standings")
    public ResponseEntity<?> getTeamsOfLeagueAndSeason(@RequestBody @Valid StandingRequest request) {
        List<Standing> standings = leagueService.findStandingBySeasonLeagueAndGroup(request);
        return ResponseEntity.ok(standings);
    }
    @Autowired
    private GroupRepository groupRepository;
    @GetMapping("/groups")
    public ResponseEntity<?> getGroups(@RequestBody GroupRequest request) {
        try {
            List<Group> groups = groupRepository.findAllBySeasonAndLeague(request.getSeason(), request.getId_league());
            // Creiamo una nuova lista per contenere i nomi dei gruppi
            List<String> groupNames = new ArrayList<>();
            // Iteriamo attraverso la lista di gruppi
            for (Group group : groups) {
                // Aggiungiamo il nome del gruppo alla nuova lista
                groupNames.add(group.getGroupName());
            }
            // Restituiamo la lista di nomi di gruppi come risposta
            return ResponseEntity.ok(groupNames);
        } catch (Exception e) {
            // Gestiamo eventuali eccezioni
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
