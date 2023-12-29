package volley_net.volley_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import volley_net.volley_net.entity.Bet;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Integer> {

    /**
     *
     * @param week filtro per giornata
     * @param season filtro per stagione
     * @param id_league filtro per lega
     * @return lista di scommesse di una giornata di una lega di una stagione
     */
    @Query(value = "SELECT  new volley_net.volley_net.entity.Bet(b) " +
            "From Bet b " +
            "JOIN Game g on g.id_game=b.id_game.id_game " +
            "JOIN Team_season ts on ts.id_league.id_league=g.id_league.id_league " +
            "WHERE ts.id_league.id_league=:id_league and ts.id_season.year=:season and g.week=:week")
    List<Bet> ListOfBets(@Param("week") int week,@Param("season") int season, @Param("id_league") int id_league);
}
