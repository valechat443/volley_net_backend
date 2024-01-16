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
    /**
     * query per trovare una lega dal suo nome
     * @param name nome della lega
     * @return un oggetto lega
     */
    Optional<League> findByName(String name);


    /**
     *  query per trovare la lista delle leghe finite
     * @param oggi data odierna
     * @return lista di leghe attualmente finite
     */

    @Query(value = "SELECT  new volley_net.volley_net.entity.League(l)" +
            "From League l " +
            "join Team_season ts on ts.id_league.id_league=l.id_league " +
            "where ts.end_date<:oggi")
    List<League> getLegheFinite(@Param("oggi")LocalDate oggi);
}