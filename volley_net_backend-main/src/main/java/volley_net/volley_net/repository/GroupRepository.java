package volley_net.volley_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import volley_net.volley_net.entity.Group;
import volley_net.volley_net.entity.League;
import volley_net.volley_net.entity.Season;

import java.util.*;
@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    @Query("SELECT g FROM Group g WHERE g.groupName = :groupName")
    Optional<Group> findByGroupName(@Param("groupName") String groupName);
    @Query(value = "SELECT g.group_name \n" +
            "FROM `group` g \n" +
            "INNER JOIN standing s ON s.id_group=g.id_group \n" +
            "INNER JOIN team_season ts ON ts.id_team_season=s.id_team_season \n" +
            "INNER JOIN season se ON se.id_season=ts.id_season \n" +
            "INNER JOIN league l ON l.id_league = ts.id_league \n" +
            "WHERE l.id_league =:id_league AND se.year=:season", nativeQuery = true)
    List<Group> findAllBySeasonAndLeague(int season, int id_league);
}
