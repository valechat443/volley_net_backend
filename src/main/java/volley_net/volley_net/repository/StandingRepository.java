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
    @Query(value = "SELECT s.* From standing s \n" +
            "JOIN team_season ts ON ts.id_team_season=s.id_team_season \n" +
            "JOIN league l ON l.id_league=ts.id_league \n" +
            "JOIN season se ON se.id_season=ts.id_season \n" +
            "JOIN `group` g ON g.id_group=s.id_group \n" +
            "where se.id_season=:id_season AND l.id_league=:id_league AND g.id_group=:id_group \n" +
            "ORDER BY s.position", nativeQuery = true)
    List<Standing> getStanding(@Param("id_season") int id_season, @Param("id_league") int id_league, @Param("id_group") int id_group);

 /*
    @Query(value = "SELECT new volley_net.volley_net.entity.Standing(s) From Standing s  " +
            "JOIN Team_season ts ON ts.id_team_season=s.id_team_season.id_team_season " +
            "JOIN League l ON l.id_league=ts.id_league.id_league " +
            "JOIN Season se ON se.id_season=ts.id_season.id_season " +
            "JOIN Group g ON g.id_group=s.id_group.id_group " +
            "where se.id_season=:id_season AND l.id_league=:id_league AND g.id_group=:id_group " +
            "ORDER BY s.position" )

     */
}