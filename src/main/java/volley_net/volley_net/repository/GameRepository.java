package volley_net.volley_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import volley_net.volley_net.entity.Game;
import volley_net.volley_net.entity.Period;
import volley_net.volley_net.entity.Score;

import java.time.LocalDate;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {

    /**
     * query per trovare una partita dall'id_game
     * @param id_game identificatore di {@link Game}
     * @return il {@link Game} con l'id_game passato
     */
    @Query(value = "SELECT  new volley_net.volley_net.entity.Game(" +
            "g) " +
            "from Game g " +
            "WHERE g.id_game=:id_game"

    )
    Game GetGameByIdGame(@Param("id_game") int id_game);

    /**
     * query per trovare la giornata massima di una lega di uan stagione
     * @param season identificativo di {@link volley_net.volley_net.entity.Season}
     * @param id_league identificativo di {@link volley_net.volley_net.entity.League}
     * @return giornata massima di una lega di una {@link volley_net.volley_net.entity.Season}
     */
    @Query(value = "SELECT DISTINCT g.week from Game g " +
            "JOIN Team_season ts on ts.id_league.id_league=g.id_league.id_league " +

            "WHERE ts.id_league.id_league=:id_league and g.id_season.year=:season")

    List<String> ListOfWeek(@Param("season") int season, @Param("id_league") int id_league);

    /**
     * query per trovare la lista di Score di una partita
     * @param id_game identificativo di {@link Game}
     * @return lista di Score {@link List<Score>} di una partita
     */
    @Query(value = "SELECT  new volley_net.volley_net.entity.Score(" +
            "s) " +
            "from Score s " +
            "WHERE s.id_game.id_game=:id_game")
    List<Score> GetScoreFromIdGame(@Param("id_game") int id_game);


    /**
     * query per trovare la lista di game di una giornata di uan lega di una stagione
     * @param week filtro per giornata
     * @param season identificativo di {@link volley_net.volley_net.entity.Season}
     * @param id_league identificativo di {@link volley_net.volley_net.entity.League}
     * @return Lista di game {@link List<Game>} di una giornata di uan lega di una stagione
     */
    @Query(value = "SELECT DISTINCT new volley_net.volley_net.entity.Game (g) " +
            "FROM Game g " +
            "JOIN Team_season ts on ts.id_league.id_league=g.id_league.id_league " +

            "WHERE ts.id_league.id_league=:id_league and g.id_season.year=:season and g.week=:week")

    List<Game> GetListOfGameByWeek(@Param("week") String week,@Param("season") int season, @Param("id_league") int id_league);

    /**
     * query per trovate la lista di partite successive alla data passata
     * @param data data su cui filtrare
     * @return Lista di game {@link List<Game>} successive alla data passata
     */
    @Query(value = "SELECT DISTINCT new volley_net.volley_net.entity.Game (g)" +
            "FROM Game g " +
            "WHERE g.date>=:data")
    List<Game> GetGameAfterDate(@Param("data")LocalDate data);

    /**
     * query per trovare l'ultima partita finita di una lega
     * @param id_league identificativo di {@link volley_net.volley_net.entity.League}
     * @return  l'ultimo {@link Game} finita di una lega
     */
    @Query(value = "SELECT  new volley_net.volley_net.entity.Game (g) " +
            "FROM Game g " +
            "WHERE g.id_league.id_league=:id_league and g.status='Finished'" +
            "ORDER by g.date DESC " +
            "LIMIT 1")
    Game GetGameRecente(@Param("id_league") int id_league);
    /**
     * query per trovare le ultime cinque partite finite di una lega
     * @param id_league identificativo di {@link volley_net.volley_net.entity.League}
     * @return  li ultimi cinque {@link Game} finite di una lega
     */
    @Query(value = "SELECT  new volley_net.volley_net.entity.Game (g) " +
            "FROM Game g " +
            "WHERE g.id_league.id_league=:id_league and g.status='Finished'" +
            "ORDER by g.date DESC " +
            "LIMIT 5")
    List<Game> GetListGameRecenti(@Param("id_league") int id_league);

    /**
     * query per trovare la lista di period di uno score
     * @param id_score identificativo di {@link Score}
     * @return Lista di tempi {@link List<Period>} di uno score
     */
    @Query(value = "SELECT  new volley_net.volley_net.entity.Period (p) " +
            "FROM Period p " +
            "WHERE p.id_score.id_score=:id_score")
    List<Period> GetPeriodFromScore(@Param("id_score") int id_score);

    /**
     * query per trovare gli odd dei vincitori di ieri
     * @return
     */
    @Query(value = "SELECT g.id_game, s.id_team, s.home, g.home_odds, g.away_odds " +
            "From game g Inner Join score s on g.id_game=s.id_game " +
            "WHERE g.date=:oggi AND s.sets=3;", nativeQuery = true)
    List<String> getOddsWinnerGame(@Param("oggi") LocalDate oggi);
}
