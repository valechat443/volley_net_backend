package volley_net.volley_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import volley_net.volley_net.entity.Game;
import volley_net.volley_net.entity.League;
import volley_net.volley_net.entity.Season;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LeagueRepository extends JpaRepository<League, Integer> {
    Optional<League> findByName(String name);

    @Query(value = "SELECT  new volley_net.volley_net.entity.League(l)" +
            "From League l " +
            "join Team_season ts on ts.id_league.id_league=l.id_league " +
            "where ts.end_date<:oggi")
    List<League> getLegheFinite(@Param("oggi")LocalDate oggi);
}