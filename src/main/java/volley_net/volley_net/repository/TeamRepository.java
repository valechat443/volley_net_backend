package volley_net.volley_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import volley_net.volley_net.entity.Statistic;
import volley_net.volley_net.entity.Team;
import volley_net.volley_net.entity.Team_season;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team,Integer> {

    /**
     * query per trovare un team partendo dal suo id
     * @param id_team id del team
     * @return oggetto team
     */
    @Query(value = "SELECT  new volley_net.volley_net.entity.Team(" +
            "t) " +
            "from Team t " +
            "WHERE t.id_team=:id_team"

    )
    Team GetTeamByIdTeam(@Param("id_team") int id_team);

    /**
     * query per trovare una lista di team di una lega, in una stagione
     * @param season id di season
     * @param id_league id di lega
     * @return lista di team
     */
    @Query(value = "SELECT  new volley_net.volley_net.entity.Team(" +
            "t) " +
            "from Team t " +
            "JOIN Team_season ts ON t.id_team = ts.id_team.id_team "+
            "WHERE ts.id_season.year = :season AND ts.id_league.id_league = :id_league"
    )
    List<Team> GetListOfTeam(@Param("season") int season, @Param("id_league") int id_league );

    /**
     * query pr trovare una team_season partendo dal suo id
     * @param id_team id del team
     * @param id_season id della season
     * @return oggetto team_season
     */
    @Query(value = "SELECT  new volley_net.volley_net.entity.Team_season(" +
            "ts) " +
            "from Team_season ts " +
            "WHERE ts.id_team.id_team=:id_team AND ts.id_season.id_season=:id_season"
    )
    Team_season GetTeamSeasonByIdTeamIdSeason(@Param("id_team") int id_team, @Param("id_season") int id_season);

    /**
     *  query per trovare le statistic di un team, in una lega in una stagione
     * @param id_team
     * @param id_league
     * @param id_season
     * @return lista di statistiche
     */
    @Query(value = "Select new volley_net.volley_net.entity.Statistic(s)" +
            " From Statistic s " +
            "Join Team_season ts ON ts.id_team_season=s.id_team_season.id_team_season " +
            "Join Team t On t.id_team=ts.id_team.id_team " +
            "Where t.id_team=:id_team and ts.id_league.id_league=:id_league and ts.id_season.id_season=:id_season")
    List<Statistic> GetStatistic(@Param("id_team") int id_team,@Param("id_league") int id_league,@Param("id_season") int id_season);
}
