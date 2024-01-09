package volley_net.volley_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import volley_net.volley_net.entity.Game;
import volley_net.volley_net.entity.League;
import volley_net.volley_net.entity.Season;

import java.util.List;
import java.util.Optional;

public interface LeagueRepository extends JpaRepository<League, Integer> {
    Optional<League> findByName(String name);

    @Query(value = "SELECT l.id_league FROM league l JOIN team_season ts ON ts.id_league=l.id_league JOIN season s ON s.id_season=ts.id_season WHERE s.id_season=2023", nativeQuery = true)
    List<League> findIdLeague();
}