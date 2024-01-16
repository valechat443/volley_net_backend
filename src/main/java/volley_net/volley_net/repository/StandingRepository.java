package volley_net.volley_net.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import volley_net.volley_net.entity.Season;
import volley_net.volley_net.entity.Standing;

import java.util.List;

@Repository
public interface StandingRepository extends JpaRepository<Standing, Integer> {
    /**
     *  query oer trovare la classifica di un gruppi, di una lega, in una stagione
     * @param id_season id dela season
     * @param id_league id della lega
     * @param id_group id del gruppo
     * @return lista di standing
     */
    @Query(value = "SELECT s.* From standing s \n" +
            "JOIN team_season ts ON ts.id_team_season=s.id_team_season \n" +
            "JOIN league l ON l.id_league=ts.id_league \n" +
            "JOIN season se ON se.id_season=ts.id_season \n" +
            "JOIN `group` g ON g.id_group=s.id_group \n" +
            "where se.id_season=:id_season AND l.id_league=:id_league AND g.id_group=:id_group \n" +
            "ORDER BY s.position", nativeQuery = true)
    List<Standing> getStanding(@Param("id_season") int id_season, @Param("id_league") int id_league, @Param("id_group") int id_group);

    /**
     * query per trovare uan classifica partendo dal team_season
     * @param id_ts id del team_season
     * @return lista di standing
     */
    @Query(value = "SELECT  new volley_net.volley_net.entity.Standing(s)" +
            "from Standing s " +
            "where s.id_team_season.id_team_season=:id_ts")
    List<Standing> getStandingFromTeamSeason(@Param("id_ts") int id_ts);


}