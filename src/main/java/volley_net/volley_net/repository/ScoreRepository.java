package volley_net.volley_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import volley_net.volley_net.entity.Score;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score,Integer> {

        /**
         * query per ottenere uno score con un determinato team, in determinato game
         * @param id_team identificatore di {@link volley_net.volley_net.entity.Team}
         * @param id_game identificatore di {@link volley_net.volley_net.entity.Game}
         * @return oggetto {@link Score}
         */
        @Query(value = "SELECT  new volley_net.volley_net.entity.Score(s) " +
                "From Score s " +
                "where s.id_game.id_game=:id_game and s.id_team.id_team=:id_team")
        Score GetScoreByIdTeamAndIdGame(@Param("id_team") int id_team,@Param("id_game") int id_game);
}
