package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_game;

    @ManyToOne
    @JoinColumn(name="id_league", nullable = false)
    private League id_league;

    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private LocalTime time;

    @Column(length = 3,nullable = false)
    private String timezone;

    @Column(length = 50,nullable = false)
    private String status;
    @Column(nullable = true)
    private float home_odds;
    @Column(nullable = true)
    private float away_odds;

    @Column(length = 11,nullable = false)
    private int week;
}
