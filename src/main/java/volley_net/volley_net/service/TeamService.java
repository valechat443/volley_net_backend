package volley_net.volley_net.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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
import volley_net.volley_net.payload.response.GetTeamStatisticResponse;
import volley_net.volley_net.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamService {

    private final TeamRepository teamRepository;
    private final GameService gameService;
    /**
     *statistiche di un singolo team
     *
     */
    public ResponseEntity<?> getStatistic(StatisticRequest request) {
        try{
            List<Statistic>elenco =teamRepository.GetStatistic(request.getId_team());
            List<GetTeamStatisticResponse> response = new ArrayList<>();
            for(Statistic s:elenco){
                response.add(new GetTeamStatisticResponse(s.getId_team_season().getId_team().getId_team(),
                        s.getId_team_season().getId_team().getName(),s.getId_team_season().getId_team().getLogo(),s.getId_team_season().getId_team().isNational(),
                        s.getPlayed_home(),s.getPlayed_away(),s.getWins_home(),s.getWins_away(),s.getLosses_home(),s.getLosses_away(),s.getDraws_home(),s.getDraws_away(),s.getFor_goals_home(),s.getFor_goals_away(),s.getAgainst_goals_home(),s.getAgainst_goals_away()));
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("ricerca senza risultati", HttpStatus.NOT_FOUND);
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


    public Team salva_team(JSONObject game,int home){
    try{
        JSONObject team=new JSONObject();
        Team t = new Team();
        if(home==1){
            team=game.getJSONObject("teams").getJSONObject("home");
        }else{
            team=game.getJSONObject("teams").getJSONObject("away");
        }
        if(teamRepository.GetTeamByIdTeam(team.getInt("id"))!=null){
            t=new Team(team.getInt("id"),team.getString("name"),team.getString("logo"),false);
            teamRepository.save(t);
        }
        return t;
    }catch (Exception e){
        return null;
    }
    }
}