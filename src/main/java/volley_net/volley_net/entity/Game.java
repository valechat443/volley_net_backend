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

    private LocalDate date;

    private LocalTime time;

    @Column(length = 3)
    private String timezone;

    @Column(length = 50)
    private String status;

    private float home_odds;

    private float away_odds;

    @Column(length = 11)
    private int week;
}
