package volley_net.volley_net.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import volley_net.volley_net.entity.League;
import volley_net.volley_net.entity.Season;
import volley_net.volley_net.entity.Team_season;

import java.time.LocalDate;
import java.util.*;

@Repository
public interface TeamSeasonRepository extends JpaRepository<Team_season, Integer> {
    /**
     *  query per trovare una lista di team_season partendo dall'id della stagione
     * @param season id della season
     * @return lista di team_season
     */
    @Query("SELECT  ts FROM Team_season ts WHERE ts.id_season = :season")
    List<Team_season> findBySeason(Season season);


    /**
     * query per trovare un team_season di una lega, di uan season, con la data più recente
     * @param id_league id della lega
     * @param season id della stagione
     * @return oggetto team_season
     */

    @Query(value="SELECT    new volley_net.volley_net.entity.Team_season(ts) " +
            "from Team_season ts " +
            "where ts.id_league.id_league=:id_league and ts.id_season.year=:season " +
            "order by ts.end_date " +
            "LIMIT 1")
    Team_season get_ts_date(@Param("id_league") int id_league,@Param("season") int season);


    /**
     * query per trovare il team_season di un team, in una aga, in una stagione
     * @param id_league id della lega
     * @param season id della stagione
     * @param id_team id del team
     * @return oggetto team_season
     */

    @Query(value="SELECT    new volley_net.volley_net.entity.Team_season(ts) " +
            "from Team_season ts " +
            "where ts.id_league.id_league=:id_league and ts.id_season.year=:season and ts.id_team.id_team=:id_team"
            )
    Team_season get_ts_completo(@Param("id_league") int id_league,@Param("season") int season,@Param("id_team") int id_team);


    /**
     * query per trovare una lista di team_season finiti
     * @param oggi data odierna
     * @return lista di team_season
     */

    @Query(value = "SELECT  new volley_net.volley_net.entity.Team_season(ts) " +
            "From Team_season ts " +
            "where ts.end_date<:oggi " +
            "group by ts.id_league.id_league")
    List<Team_season> getTeamSeasonFiniti(@Param("oggi") LocalDate oggi);
}