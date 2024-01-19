package volley_net.volley_net.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import volley_net.volley_net.entity.*;
import volley_net.volley_net.payload.request.GroupRequest;
import volley_net.volley_net.payload.request.ListOfLeagueRequest;
import volley_net.volley_net.payload.request.StandingRequest;
import volley_net.volley_net.payload.response.GetListOfLeagueResponse;
import volley_net.volley_net.payload.response.GetStandingResponse;
import volley_net.volley_net.payload.response.GetWeekMaxResponse;
import volley_net.volley_net.repository.*;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeagueService {
    private final SeasonRepository seasonRepository;
    private final TeamSeasonRepository teamSeasonRepository;
    private final StandingRepository standingRepository;
    private final LeagueRepository leagueRepository;
    private final GroupRepository groupRepository;


    /**
     * metodo per restituire una lista di leghe di una season
     * @param year anno della stagione
     * @return lista di {@link GetListOfLeagueResponse} o una lista vuota
     */
    public ResponseEntity<?> getLeaguesFromSeason(int year) {
        List<GetListOfLeagueResponse> leagues = new ArrayList<>();
        try {
            Optional<Season> desiredSeason = seasonRepository.findByYear(year);
            if (!desiredSeason.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Season not found");
            }
            List<Team_season> teamSeasons = teamSeasonRepository.findBySeason(desiredSeason.get());
            List<Integer> id_leagues= new ArrayList<>();
            for (Team_season teamSeason : teamSeasons) {
                if(!id_leagues.contains(teamSeason.getId_league().getId_league())) {
                    leagues.add(new GetListOfLeagueResponse(teamSeason.getId_league().getId_league(),teamSeason.getId_league().getName(),teamSeason.getId_league().getType(),teamSeason.getId_league().getLogo(),teamSeason.getStart_date(),teamSeason.getEnd_date()));
                    id_leagues.add(teamSeason.getId_league().getId_league());
                }
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", e);
        }
        return new ResponseEntity<>(leagues, HttpStatus.OK);
    }


    /**
     * metodo per restituire la classifica di una lega di una stagione
     * @param request {@link StandingRequest}
     * @return lista di {@link GetStandingResponse} o una lista vuota
     */
    public ResponseEntity<?> findStandingBySeasonLeagueAndGroup(StandingRequest request) {
        try {
            List<Standing> elenco =standingRepository.getStanding(request.getId_season(),request.getId_league(),request.getId_group());
            List<GetStandingResponse> response = new ArrayList<>();
            for(Standing s:elenco){
                response.add( new GetStandingResponse(s.getId_team_season().getId_team().getName(),s.getPosition(),s.getPoints(),s.getForm(),s.getZona(),s.getId_team_season().getId_team().getLogo()));

            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            // Gestiamo eventuali eccezioni
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());

        }
    }


    /**
     * metodo per restituire i gruppi di una lega di una determinata stagione
     * @param request {@link GroupRequest}
     * @return lista si stringhe contenente i nomi dei gruppi o una lista vuota
     */
    public ResponseEntity<?> getGroup(GroupRequest request){
        try {
            List<Group> groups = groupRepository.findAllBySeasonAndLeague(request.getSeason(), request.getId_league());
            // Creiamo una nuova lista per contenere i nomi dei gruppi
            List<String> groupNames = new ArrayList<>();
            // Iteriamo attraverso la lista di gruppi
            for (Group group : groups) {
                // Aggiungiamo il nome del gruppo alla nuova lista
                groupNames.add(group.getGroup_name());
            }
            // Restituiamo la lista di nomi di gruppi come risposta
            return ResponseEntity.ok(groupNames);
        } catch (Exception e) {
            // Gestiamo eventuali eccezioni
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

}