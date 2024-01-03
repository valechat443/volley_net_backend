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

}
