package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * statistiche di un team in un gruppo, in una lega, in una stagione
 */
public class Statistic {

    /**
     * identificatore di statistic
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_statistic;

    /**
     * {@link Team_season} mix di {@link Season}, {@link  League} e {@link  Team}, da usare come chiave
     */
    @OneToOne
    @JoinColumn(name="id_team_season",nullable=false)
    private Team_season id_team_season;

    /**
     * partite giocate a casa
     */
    @Column(length=3,nullable = false)
    private int played_home=0;

    /**
     * partite giocate fuori casa
     */
    @Column(length=3,nullable = false)
    private int played_away=0;

    /**
     * partite vinte a casa
     */
    @Column(length=3,nullable = false)
    private int wins_home=0;

    /**
     * partite vinte fuori casa
     */
    @Column(length=3,nullable = false)
    private int wins_away=0;

    /**
     * partite perse a casa
     */
    @Column(length=3,nullable = false)
    private int losses_home=0;

    /**
     * partite perse fuori casa
     */
    @Column(length=3,nullable = false)
    private int losses_away=0;

    /**
     * partite pareggiate a casa
     */
    @Column(length=3,nullable = false)
    private int draws_home=0;
    /**
     * partite pareggiate fuori casa
     */
    @Column(length=3,nullable = false)
    private int draws_away=0;

    /**
     * goals fatti a casa
     */
    @Column(length=3,nullable = false)
    private int for_goals_home=0;

    /**
     * goals fatti fuori casa
     */
    @Column(length=3,nullable = false)
    private int for_goals_away=0;
    /**
     * goals subiti a casa
     */
    @Column(length=3,nullable = false)
    private int against_goals_home=0;
    /**
     * goals subiti fuori casa
     */
    @Column(length=3,nullable = false)
    private int against_goals_away=0;

    /**
     * costruttore che crea un oggetto statistic partendo da uno già esistente
     * @param s oggetto statistic
     */
    public Statistic(Statistic s){
        this.id_statistic = s.id_statistic;
        this.id_team_season = s.id_team_season;
        this.played_home = s.played_home;
        this.played_away = s.played_away;
        this.wins_home = s.wins_home;
        this.wins_away = s.wins_away;
        this.losses_home = s.losses_home;
        this.losses_away = s.losses_away;
        this.draws_home = s.draws_home;
        this.draws_away = s.draws_away;
        this.for_goals_home = s.for_goals_home;
        this.for_goals_away = s.for_goals_away;
        this.against_goals_home = s.against_goals_home;
        this.against_goals_away = s.against_goals_away;
    }

    /**
     * costruttore per creare un oggetto statistic senza un id_statistic per creato
     * @param id_team_season identificatore di {@link  Team_season}
     * @param played_home partite giocate in casa
     * @param played_away partite giocate fuori casa
     * @param wins_home partite vinte in casa
     * @param wins_away partite vinte fuori casa
     * @param losses_home partite perse in casa
     * @param losses_away partite perse fuori casa
     * @param draws_home partite pareggiate in casa
     * @param draws_away partite pareggiate fuori casa
     * @param for_goals_home goals fatti in casa
     * @param for_goals_away goals fatti fuori casa
     * @param against_goals_home goals subito in casa
     * @param against_goals_away goals subiti fuori casa
     */
    public Statistic(Team_season id_team_season, int played_home, int played_away, int wins_home, int wins_away, int losses_home, int losses_away, int draws_home, int draws_away, int for_goals_home, int for_goals_away, int against_goals_home, int against_goals_away) {
        this.id_team_season = id_team_season;
        this.played_home = played_home;
        this.played_away = played_away;
        this.wins_home = wins_home;
        this.wins_away = wins_away;
        this.losses_home = losses_home;
        this.losses_away = losses_away;
        this.draws_home = draws_home;
        this.draws_away = draws_away;
        this.for_goals_home = for_goals_home;
        this.for_goals_away = for_goals_away;
        this.against_goals_home = against_goals_home;
        this.against_goals_away = against_goals_away;
    }
}
