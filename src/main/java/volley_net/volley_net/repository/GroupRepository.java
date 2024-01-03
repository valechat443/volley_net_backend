package volley_net.volley_net.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import volley_net.volley_net.entity.Group;


public interface GroupRepository extends JpaRepository<Group,Integer> {
    @Query(value = "SELECT s.id_group " +
            "from standing s " +
            "WHERE s.id_team_season=:id_team_season", nativeQuery = true
    )
    int GetIdGroupByIdTeamSeason(@Param("id_team_season") int id_team_season);

    @Query(value = "SELECT  new volley_net.volley_net.entity.Group(" +
            "g) " +
            "from Group g " +
            "WHERE g.id_group=:id_group", nativeQuery = true
    )
    Group GetGroupByIdGroup(@Param("id_group") int id_group);
}
