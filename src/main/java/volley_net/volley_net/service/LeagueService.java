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
     *lista di tutte le leghe di una stagione
     *
     */
    public List<League> getLeaguesFromSeason(int year) {
        List<League> leagues = new ArrayList<>();
        try {
            Optional<Season> desiredSeason = seasonRepository.findByYear(year);
            if (!desiredSeason.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Season not found");
            }
            List<Team_season> teamSeasons = teamSeasonRepository.findBySeason(desiredSeason.get());
            for (Team_season teamSeason : teamSeasons) {
                leagues.add(teamSeason.getId_league());
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", e);
        }
        return leagues;
    }


    /**
     *classifica di una lega di una stagione
     *
     */
    public List<Standing> findStandingBySeasonLeagueAndGroup(StandingRequest request) {
        return standingRepository.getStanding(request.getId_season(),request.getId_league(),request.getId_group());
    }




    /**
     *gruppi di una lega di una stagione
     *
     */
    public ResponseEntity<?> getGroupsBySeasonAndLeague(GroupRequest request) {
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