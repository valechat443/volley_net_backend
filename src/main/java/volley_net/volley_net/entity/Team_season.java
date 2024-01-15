package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * tabella di intermezzo per unire team, season e league in un unico posto
 */
public class Team_season {
    /**
     * identificatore di team_season
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_team_season;
    /**
     * lega per periodo
     */
    @ManyToOne
    @JoinColumn(name="id_league", nullable = false)
    private League id_league;

    /**
     * stagione del periodo
     */
    @ManyToOne
    @JoinColumn(name="id_season", nullable = false)
    private Season id_season;
    /**
     * team di questo periodo
     */
    @ManyToOne
    @JoinColumn(name="id_team", nullable = false)
    private Team id_team;

    /**
     *     data di inizio della lega
      */

    @Column(nullable = false)
    private LocalDate start_date;
    /**
     * data di fine della lega
     */
    @Column(nullable = false)
    private LocalDate end_date;

    /**
     * costruttore che crea un oggetto team_season  partendo da uno già esistente
     * @param ts
     */
    public Team_season(Team_season ts) {
        this.id_team_season = ts.id_team_season;
        this.id_league = ts.getId_league();
        this.id_season = ts.getId_season();
        this.id_team = ts.getId_team();
        this.start_date = ts.getStart_date();
        this.end_date = ts.getEnd_date();
    }

    /**
     * costruttore per creare un oggetto team_season senza un id per creato
     * @param id_league
     * @param id_season
     * @param id_team
     * @param start_date
     * @param end_date
     */
    public Team_season(League id_league, Season id_season, Team id_team, LocalDate start_date, LocalDate end_date) {
        this.id_league = id_league;
        this.id_season = id_season;
        this.id_team = id_team;
        this.start_date = start_date;
        this.end_date = end_date;
    }
}
