package volley_net.volley_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import volley_net.volley_net.entity.Season;
import volley_net.volley_net.entity.Team_season;
import java.util.*;

@Repository
public interface TeamSeasonRepository extends JpaRepository<Team_season, Integer> {
    @Query("SELECT  ts FROM Team_season ts WHERE ts.id_season = :season")
    List<Team_season> findBySeason(Season season);

    @Query(value = "select ts.id_team_season from team_season ts where ts.id_league=:id_league AND ts.id_team=:id_team AND ts.id_season=:id_season", nativeQuery = true)
    int findIdTeamSeason(@Param("id_league") int id_league, @Param("id_team") int id_team, @Param("id_season")int id_season);
}