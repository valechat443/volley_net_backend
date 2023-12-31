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
     *
     * @param id_game id della partita
     * @return la partita con l'id_game passato
     */
    @Query(value = "SELECT  new volley_net.volley_net.entity.Game(" +
            "g) " +
            "from Game g " +
            "WHERE g.id_game=:id_game"

    )
    Game GetGameByIdGame(@Param("id_game") int id_game);

    /**
     *
     * @param season filtro per stagione
     * @param id_league filtro per lega
     * @return giornata massima di una lega di uan stagione
     */
    @Query(value = "SELECT MAX(g.week) from Game g " +
            "JOIN Team_season ts on ts.id_league.id_league=g.id_league.id_league " +
            "WHERE ts.id_league.id_league=:id_league and ts.id_season.year=:season")
    Integer MaxWeek(@Param("season") int season, @Param("id_league") int id_league);

    /**
     *
     * @param id_game id della partita
     * @return lista di Score di una partita
     */
    @Query(value = "SELECT  new volley_net.volley_net.entity.Score(" +
            "s) " +
            "from Score s " +
            "WHERE s.id_game.id_game=:id_game")
    List<Score> GetScoreFromIdGame(@Param("id_game") int id_game);

    /**
     *
     * @param season filtro per stagione
     * @param id_league filtro per lega
     * @return Lista di partite di una lega di una stagione
     */
    @Query(value = "SELECT DISTINCT new volley_net.volley_net.entity.Game (g) " +
            "FROM Game g " +
            "JOIN Team_season ts on ts.id_league.id_league=g.id_league.id_league " +
            "WHERE ts.id_league.id_league=:id_league and ts.id_season.year=:season")
    List<Game> GetListOfGame(@Param("season") int season, @Param("id_league") int id_league);

    /**
     *
     * @param week filtro per giornata
     * @param season filtro per stagione
     * @param id_league filtro per lega
     * @return Lisrta di game di una giornata di uan lega di una stagione
     */
    @Query(value = "SELECT DISTINCT new volley_net.volley_net.entity.Game (g) " +
            "FROM Game g " +
            "JOIN Team_season ts on ts.id_league.id_league=g.id_league.id_league " +
            "WHERE ts.id_league.id_league=:id_league and ts.id_season.year=:season and g.week=:week")
    List<Game> GetListOfGameByWeek(@Param("week") int week,@Param("season") int season, @Param("id_league") int id_league);

    /**
     *
     * @param data data da passare
     * @return Lista di partite successive alla data passata
     */
    @Query(value = "SELECT DISTINCT new volley_net.volley_net.entity.Game (g)" +
            "FROM Game g " +
            "WHERE g.date>=:data")
    List<Game> GetGameAfterDate(@Param("data")LocalDate data);

    /**
     *
     * @param id_league id della lega
     * @return  l'ultima partita finita di una lega
     */
    @Query(value = "SELECT  new volley_net.volley_net.entity.Game (g) " +
            "FROM Game g " +
            "WHERE g.id_league.id_league=:id_league and g.status='Finished'" +
            "ORDER by g.date DESC " +
            "LIMIT 1")
    Game GetGameRecente(@Param("id_league") int id_league);
    @Query(value = "SELECT  new volley_net.volley_net.entity.Game (g) " +
            "FROM Game g " +
            "WHERE g.id_league.id_league=:id_league and g.status='Finished'" +
            "ORDER by g.date DESC " +
            "LIMIT 5")
    List<Game> GetListGameRecenti(@Param("id_league") int id_league);

    /**
     *
     * @param id_score id dello score
     * @return Lista di period di uno score
     */
    @Query(value = "SELECT  new volley_net.volley_net.entity.Period (p) " +
            "FROM Period p " +
            "WHERE p.id_score.id_score=:id_score")
    List<Period> GetPeriodFromScore(@Param("id_score") int id_score);


}
