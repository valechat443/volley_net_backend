package volley_net.volley_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import volley_net.volley_net.entity.Game;
import volley_net.volley_net.entity.Score;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {

    @Query(value = "SELECT  new volley_net.volley_net.entity.Game(" +
            "g) " +
            "from Game g " +
            "WHERE g.id_game=:id_game"

    )
    Game GetGameByIdGame(@Param("id_game") int id_game);

    @Query(value = "SELECT MAX(g.week) from Game g " +
            "JOIN Team_season ts on ts.id_league.id_league=g.id_league.id_league " +
            "WHERE ts.id_league.id_league=:id_league and ts.id_season.year=:season")
    int MaxWeek(@Param("season") int season, @Param("id_league") int id_league);

    @Query(value = "SELECT  new volley_net.volley_net.entity.Score(" +
            "s) " +
            "from Score s " +
            "WHERE s.id_game.id_game=:id_game")
    List<Score> GetScoreFromIdGame(@Param("id_game") int id_game);

    @Query(value = "SELECT DISTINCT new volley_net.volley_net.entity.Game (g) " +
            "FROM Game g " +
            "JOIN Team_season ts on ts.id_league.id_league=g.id_league.id_league " +
            "WHERE ts.id_league.id_league=:id_league and ts.id_season.year=:season")
    List<Game> GetListOfGame(@Param("season") int season, @Param("id_league") int id_league);
}
