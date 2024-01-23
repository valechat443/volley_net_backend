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
/**
 * partita giocata o da giocare
 */
public class Game {

    /**
     * identificativo di game
     */
    @Id
    @EqualsAndHashCode.Include
    private int id_game;
    /**
     * {@link League} in cui la partita è stata giocata
     */
    @ManyToOne
    @JoinColumn(name="id_league", nullable = false)
    private League id_league;

    /**
     * {@link  Season} in cui la partita è giocata
     */
    @ManyToOne
    @JoinColumn(name="id_season", nullable = false)
    private Season id_season;

    /**
     * data in cui è stata giocata la partita
     */
    @Column(nullable = false)
    private LocalDate date;
    /**
     * ora in cui è stata giocata la partita
     */
    @Column(nullable = false)
    private LocalTime time;

    /**
     * timezone in cui la partita è stata giocata
     */
    @Column(length = 3,nullable = false)
    private String timezone;

    /**
     * status della partita (non iniziata, finita, ecc...)
     */
    @Column(length = 50,nullable = false)
    private String status;
    /**
     * possibilità di vittoria della squadra di casa
     */
    @Column(nullable = true)
    private Float home_odds;
    /**
     * possibilità di vittoria della squadra ospite
     */
    @Column(nullable = true)
    private Float away_odds;

    /**
     * giornata in cui è stata giocata la partita
     */
    @Column(length = 11,nullable = false)
    private String week;

    /**
     * costruttore per creare un oggetto game partendo da uno già esistente
     * @param g oggetto  game
     */
    public Game(Game g) {
        this.id_game = g.getId_game();
        this.id_league = g.getId_league();
        this.id_season=g.getId_season();
        this.date = g.getDate();
        this.time = g.getTime();
        this.timezone = g.getTimezone();
        this.status = g.getStatus();
        this.home_odds = g.getHome_odds();
        this.away_odds = g.getAway_odds();
        this.week = g.getWeek();
    }

    /**
     * costruttore per creare un oggetto game con solo l'id_game
     * @param id_game identificativo di game
     */
    public Game(int id_game) {
        this.id_game = id_game;
    }
}
