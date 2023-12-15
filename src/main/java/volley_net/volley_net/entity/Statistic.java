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

    @Column()
    private int played_home;

    @Column()
    private int played_away;

    @Column()
    private int wins_home;

    @Column()
    private int wins_away;

    @Column()
    private int losses_home;

    @Column()
    private int losses_away;

    @Column()
    private int draws_home;

    @Column()
    private int draws_away;

    @Column()
    private int for_goals_home;

    @Column()
    private int for_goals_away;

    @Column()
    private int against_goals_home;

    @Column()
    private int against_goals_away;

}
