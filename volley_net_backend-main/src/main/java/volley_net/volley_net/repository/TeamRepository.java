package volley_net.volley_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import volley_net.volley_net.entity.Statistic;
import volley_net.volley_net.entity.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team,Integer> {


    @Query(value = "SELECT  new volley_net.volley_net.entity.Team(" +
            "t) " +
            "from Team t " +
            "WHERE t.id_team=:id_team"

    )
    Team GetTeamByIdTeam(@Param("id_team") int id_team);

    @Query(value = "SELECT  new volley_net.volley_net.entity.Team(" +
            "t) " +
            "from Team t " +
            "JOIN Team_season ts ON t.id_team = ts.id_team.id_team "+
            "WHERE ts.id_season.year = :season AND ts.id_league.id_league = :id_league"
    )
    List<Team> GetListOfTeam(@Param("season") int season, @Param("id_league") int id_league );

    @Query(value = "Select s.* From statistic s Join team_season ts ON ts.id_team_season=s.id_team_season Join team t On t.id_team=ts.id_team Where t.id_team=:id_team",nativeQuery = true)
    List<Statistic> GetStatistic(@Param("id_team") int id_team);
}
