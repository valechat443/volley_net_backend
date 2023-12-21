package volley_net.volley_net.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import volley_net.volley_net.payload.request.GroupRequest;
import volley_net.volley_net.payload.request.ListOfLeagueRequest;
import volley_net.volley_net.payload.request.StandingRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeagueService {

    /**
     *lista di tutte le leghe di ua stagione
     *
     */
    private ResponseEntity<?> get_list_of_league(ListOfLeagueRequest request) {
        return null;
    }
    /**
     *classifica di una lega di una stagione
     *
     */
    private ResponseEntity<?> get_standings(StandingRequest request) {
        return null;
    }

    /**
     *gruppi di una lega di una stagione
     *
     */
    private ResponseEntity<?> get_groups(GroupRequest request) {
        return null;
    }



}
