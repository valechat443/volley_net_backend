package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * singolo tempo giocato in una partita
 */
public class Period {

    /**
     * identificatore di period
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_period;

    /**
     * score a cui period fa riferimento
     */
    @ManyToOne
    @JoinColumn(name="id_score", nullable = false)
    private Score id_score;
    /**
     * punti fatti in un period
     */
    @Column(nullable = true)
    private Integer points;
    /**
     * numero del period (da 1-5 0 1-3)
     */
    @Column(length = 6,nullable = false)
    private String period_number;

    /**
     * costruttore per creare un oggetto period partendo da uno già esistente
     * @param p
     */
    public Period(Period p) {
        this.id_period = p.getId_period();
        this.id_score = p.getId_score();
        this.points = p.getPoints();
        this.period_number = p.getPeriod_number();
    }

    /**
     * costruttore per creare un period senza un id pre creato
     * @param id_score
     * @param points
     * @param period_number
     */
    public Period(Score id_score, int points, String period_number) {
        this.id_score = id_score;
        this.points = points;
        this.period_number = period_number;
    }
}
