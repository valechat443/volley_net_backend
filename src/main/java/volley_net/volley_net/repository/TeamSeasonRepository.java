package volley_net.volley_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import volley_net.volley_net.entity.Season;
import volley_net.volley_net.entity.Team_season;
import java.util.*;

@Repository
public interface TeamSeasonRepository extends JpaRepository<Team_season, Integer> {
    @Query("SELECT  ts FROM Team_season ts WHERE ts.id_season = :season")
    List<Team_season> findBySeason(Season season);
}