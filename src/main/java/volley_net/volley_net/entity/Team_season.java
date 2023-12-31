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
public class Team_season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_team_season;

    @ManyToOne
    @JoinColumn(name="id_league", nullable = false)
    private League id_league;

    @ManyToOne
    @JoinColumn(name="id_season", nullable = false)
    private Season id_season;

    @ManyToOne
    @JoinColumn(name="id_team", nullable = false)
    private Team id_team;

    @Column(nullable = false)
    private LocalDate start_date;
    @Column(nullable = false)
    private LocalDate end_date;
}
