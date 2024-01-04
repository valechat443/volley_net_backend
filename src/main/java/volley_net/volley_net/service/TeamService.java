package volley_net.volley_net.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import volley_net.volley_net.entity.Statistic;
import volley_net.volley_net.entity.Team;
import volley_net.volley_net.entity.User;
import volley_net.volley_net.payload.request.GetTeamRequest;
import volley_net.volley_net.payload.request.SeasonIdLeague;
import volley_net.volley_net.payload.request.SignupRequest;
import volley_net.volley_net.payload.request.StatisticRequest;
import volley_net.volley_net.payload.response.GetTeamResponse;
import volley_net.volley_net.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamService {

    private final TeamRepository teamRepository;
    /**
     *statistiche di un singolo team
     *
     */
    public List<Statistic> getStatistic(StatisticRequest request) {
        try{
        return teamRepository.GetStatistic(request.getId_team());
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    /**
     *lista di team di una determinata lega di una determinata stagione
     *
     */
    private ResponseEntity<?> get_team_list() {
        return null;
    }
    /**
     *get singolo team
     *
     */
    public ResponseEntity<?> get_team(GetTeamRequest request) {

        try{
            Team t= teamRepository.GetTeamByIdTeam(request.getId_team());
            log.info(t.toString());
            GetTeamResponse response = new GetTeamResponse(t.getId_team(),t.getName(),t.getLogo(),t.isNational());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("ricerca senza risultati", HttpStatus.NOT_FOUND);
        }

    }

    public  ResponseEntity<?> get_list_of_team(SeasonIdLeague request){

        try{
            List<Team> elenco = teamRepository.GetListOfTeam(request.getSeason(), request.getId_league());
            return new ResponseEntity<>(elenco, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("ricerca senza risultati", HttpStatus.NOT_FOUND);
        }
    }
}