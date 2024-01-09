package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_statistic;

    @OneToOne
    @JoinColumn(name="id_team_season",nullable=false)
    private Team_season id_team_season;

    @Column(length=3,nullable = false)
    private int played_home=0;

    @Column(length=3,nullable = false)
    private int played_away=0;

    @Column(length=3,nullable = false)
    private int wins_home=0;

    @Column(length=3,nullable = false)
    private int wins_away=0;

    @Column(length=3,nullable = false)
    private int losses_home=0;

    @Column(length=3,nullable = false)
    private int losses_away=0;

    @Column(length=3,nullable = false)
    private int draws_home=0;

    @Column(length=3,nullable = false)
    private int draws_away=0;

    @Column(length=3,nullable = false)
    private int for_goals_home=0;

    @Column(length=3,nullable = false)
    private int for_goals_away=0;

    @Column(length=3,nullable = false)
    private int against_goals_home=0;

    @Column(length=3,nullable = false)
    private int against_goals_away=0;


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
