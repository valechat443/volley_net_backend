package volley_net.volley_net.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import volley_net.volley_net.entity.Group;
import volley_net.volley_net.entity.League;
import volley_net.volley_net.entity.Season;
import volley_net.volley_net.entity.Standing;

import java.util.*;


public interface GroupRepository extends JpaRepository<Group,Integer> {
    /**
     * query per trovare l'id del group da un team_season
     * @param id_team_season identificativo di {@link volley_net.volley_net.entity.Team_season}
     * @return uno {@link Standing} con l'id del group da un team_season
     */
    @Query(value = "SELECT  new volley_net.volley_net.entity.Standing(s.id_group) " +
            "from Standing s " +
            "WHERE s.id_team_season.id_team_season=:id_team_season"
    )
    Standing GetIdGroupByIdTeamSeason(@Param("id_team_season") int id_team_season);

    /**
     * query per trovare un gruppo partendo dal suo id
     * @param id_group identificatore di {@link Group}
     * @return un oggetto gruppo
     */
    @Query(value = "SELECT  new volley_net.volley_net.entity.Group(" +
            "g) " +
            "from Group g " +
            "WHERE g.id_group=:id_group"
    )
    Group GetGroupByIdGroup(@Param("id_group") int id_group);

    /**
     * query per trovare un gruppo partendo dal suo nome
     * @param groupName nome del gruppo
     * @return un oggetto {@link Group}
     */
    @Query("SELECT g FROM Group g WHERE g.group_name = :groupName")
    Group findByGroupName(@Param("groupName") String groupName);

    /**
     *  query per trovare i nomi dei gruppi di una lega in una season
     * @param season identificativo di {@link volley_net.volley_net.entity.Season}
     * @param id_league identificativo di {@link volley_net.volley_net.entity.League}
     * @return lista di gruppi {@link List<Group>} di una lega di una season
     */
    @Query(value = "SELECT DISTINCT new volley_net.volley_net.entity.Group(g.group_name) " +
            "FROM Group g " +
            "INNER JOIN Standing s ON s.id_group.id_group=g.id_group " +
            "INNER JOIN Team_season ts ON ts.id_team_season=s.id_team_season.id_team_season " +
            "INNER JOIN Season se ON se.id_season=ts.id_season.id_season " +
            "INNER JOIN League l ON l.id_league = ts.id_league.id_league " +
            "WHERE l.id_league =:id_league AND se.year=:season")
    List<Group> findAllBySeasonAndLeague(int season, int id_league);


}
