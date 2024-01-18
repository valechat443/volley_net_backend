package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * punteggio di un team fatto in una partita (ogni partita ne ha 2)
 */
public class Score {

    /**
     * identificatore di score
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_score;

    /**
     * {@link Game} a cui lo score fa riferimento
     */
    @ManyToOne
    @JoinColumn(name="id_game", nullable = false)
    private Game id_game;

    /**
     * {@link Team} che ha giocato nella partita
     */
    @ManyToOne
    @JoinColumn(name="id_team", nullable = false)
    private Team id_team;

    /**
     * indica se il team di questo score gioca in casa
     */
    @Column(nullable = false)
    private boolean home;

    /**
     * numero di set vint dal team
     */
    @Column(nullable = true)
    private Integer sets;

    /**
     * costruttore che crea un oggetto game partendo da uno esistente
     * @param s oggetto score
     */
    public Score(Score s) {
        this.id_score = s.getId_score();
        this.id_game = s.getId_game();
        this.id_team = s.getId_team();
        this.home = s.isHome();
        this.sets = s.getSets();
    }

    /**
     * costruttore che crea un oggetto game senza un id pre creato
     * @param id_game
     * @param id_team
     * @param home
     * @param sets
     */
    public Score(Game id_game, Team id_team, boolean home, Integer sets) {
        this.id_game = id_game;
        this.id_team = id_team;
        this.home = home;
        this.sets = sets;
    }
}
