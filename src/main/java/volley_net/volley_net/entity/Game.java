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

    private Float home_odds;
    @Column(nullable = true)
    private Float away_odds;

    @Column(length = 11,nullable = false)
    private String week;

    public Game(Game g) {
        this.id_game = g.getId_game();
        this.id_league = g.getId_league();
        this.date = g.getDate();
        this.time = g.getTime();
        this.timezone = g.getTimezone();
        this.status = g.getStatus();
        this.home_odds = g.getHome_odds();
        this.away_odds = g.getAway_odds();
        this.week = g.getWeek();
    }

    public Game(int id_game) {
        this.id_game = id_game;
    }
}
