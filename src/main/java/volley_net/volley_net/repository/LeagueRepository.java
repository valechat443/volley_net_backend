package volley_net.volley_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import volley_net.volley_net.entity.Game;
import volley_net.volley_net.entity.League;
import volley_net.volley_net.entity.Season;

import java.util.Optional;

public interface LeagueRepository extends JpaRepository<League, Integer> {
    Optional<League> findByName(String name);
}
