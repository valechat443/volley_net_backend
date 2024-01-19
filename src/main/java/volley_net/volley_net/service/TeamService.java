package volley_net.volley_net.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import volley_net.volley_net.entity.Statistic;
import volley_net.volley_net.entity.Team;
import volley_net.volley_net.entity.Team_season;
import volley_net.volley_net.entity.User;
import volley_net.volley_net.payload.request.*;
import volley_net.volley_net.payload.response.GetTeamResponse;
import volley_net.volley_net.payload.response.GetTeamStatisticResponse;
import volley_net.volley_net.repository.GameRepository;
import volley_net.volley_net.repository.StatisticRepository;
import volley_net.volley_net.repository.TeamRepository;
import volley_net.volley_net.repository.TeamSeasonRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamService {
    /**
     * operazioni del database di team {@link TeamRepository}
     */
    private final TeamRepository teamRepository;
    /**
     * operazioni del database di team_season {@link TeamSeasonRepository}
     */
    private final TeamSeasonRepository teamSeasonRepository;
    /**
     * servizi legati a i file json restituiti da APi sport {@link JsonService}
     */
    private final JsonService jsonService;
    /**
     * operazioni del database di statistic {@link volley_net.volley_net.repository.StandingRepository}
     */
    private final StatisticRepository statisticRepository;

    /**
     * metodo per restituire le statistiche di un team in una lega in una determinata stagione
     * @param request {@link StatisticRequest}
     * @return Lista di {@link GetTeamStatisticResponse} con le statistiche di un team
     */
    public ResponseEntity<?> getStatistic(StatisticRequest request) {
        try{
            List<Statistic>elenco =teamRepository.GetStatistic(request.getId_team(), request.getId_lega(), request.getId_season());
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
     * metodo per restituire un team partendo dall'id_team
     * @param request {@link GetTeamRequest}
     * @return dati relativi a un determinato {@link Team}
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

    /**
     * metodo per restituire una lista di team di una lega di uan determinata stagione
     * @param request {@link SeasonIdLeague}
     * @return lista di team {@link  List<Team>} di una lega in una determinata stagione
     */
    public  ResponseEntity<?> get_list_of_team(SeasonIdLeague request){

        try{
            List<Team> elenco = teamRepository.GetListOfTeam(request.getSeason(), request.getId_league());
            return new ResponseEntity<>(elenco, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("ricerca senza risultati", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * metodo per salvare le statistiche di un team, di uan lega in una determinata stagione
     * @param request {@link SaveStatisticRequest}
     * @return messaggio sull'esito del salvataggio delle statistiche
     */
    public ResponseEntity<?> salva_statistic(SaveStatisticRequest request){
        try{
            Team_season ts=teamSeasonRepository.get_ts_completo(request.getId_league(),  request.getId_season(), request.getId_team());
            if(ts==null){
                return new ResponseEntity<>("team inesistente", HttpStatus.NOT_FOUND);
            }
            JSONObject jason = jsonService.chiamata("https://v1.volleyball.api-sports.io/teams/statistics?league="+ String.valueOf(request.getId_league()) + "&season="+ String.valueOf(request.getId_season())+"&team="+String.valueOf(request.getId_team()));
            Statistic ris=statisticRepository.save(new Statistic(ts,
                    jason.getJSONObject("response").getJSONObject("games").getJSONObject("played").getInt("home"),
                    jason.getJSONObject("response").getJSONObject("games").getJSONObject("played").getInt("away"),
                    jason.getJSONObject("response").getJSONObject("games").getJSONObject("wins").getJSONObject("home").getInt("total"),
                    jason.getJSONObject("response").getJSONObject("games").getJSONObject("wins").getJSONObject("away").getInt("total"),
                    jason.getJSONObject("response").getJSONObject("games").getJSONObject("loses").getJSONObject("home").getInt("total"),
                    jason.getJSONObject("response").getJSONObject("games").getJSONObject("loses").getJSONObject("away").getInt("total"),
                    jason.getJSONObject("response").getJSONObject("games").getJSONObject("draws").getJSONObject("home").getInt("total"),
                    jason.getJSONObject("response").getJSONObject("games").getJSONObject("draws").getJSONObject("away").getInt("total"),
                    jason.getJSONObject("response").getJSONObject("goals").getJSONObject("for").getJSONObject("total").getInt("home"),
                    jason.getJSONObject("response").getJSONObject("goals").getJSONObject("for").getJSONObject("total").getInt("away"),
                    jason.getJSONObject("response").getJSONObject("goals").getJSONObject("against").getJSONObject("total").getInt("home"),
                    jason.getJSONObject("response").getJSONObject("goals").getJSONObject("against").getJSONObject("total").getInt("away")));

            return new ResponseEntity<>("ok", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("ricerca senza risultati", HttpStatus.NOT_FOUND);
        }
    }



}