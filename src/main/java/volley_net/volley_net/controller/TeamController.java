package volley_net.volley_net.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volley_net.volley_net.entity.Statistic;
import volley_net.volley_net.payload.request.GetTeamRequest;
import volley_net.volley_net.payload.request.SeasonIdLeague;
import volley_net.volley_net.payload.request.StatisticRequest;
import volley_net.volley_net.service.TeamService;
import java.util.*;

@RestController
@RequestMapping("team")
@RequiredArgsConstructor
@CrossOrigin
public class TeamController {
    private  final TeamService teamService;

    @PostMapping("/getTeam")
    public ResponseEntity<?> get_team(@RequestBody @Valid GetTeamRequest request){
        return teamService.get_team(request);
    }

    @PostMapping("/getList")
    public ResponseEntity<?> get_list_of_team(@RequestBody @Valid SeasonIdLeague request){
        return teamService.get_list_of_team(request);
    }
    @GetMapping("/statistics")
    public ResponseEntity<List<Statistic>> getStatistics(@RequestBody @Valid StatisticRequest request) {
        List<Statistic> statistics = teamService.getStatistic(request);
        return ResponseEntity.ok(statistics);
    }



}
