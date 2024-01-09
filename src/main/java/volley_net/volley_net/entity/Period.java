package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_period;

    @ManyToOne
    @JoinColumn(name="id_score", nullable = false)
    private Score id_score;
    @Column(nullable = true)
    private Integer points;

    @Column(length = 6,nullable = false)
    private String period_number;

    public Period(Period p) {
        this.id_period = p.getId_period();
        this.id_score = p.getId_score();
        this.points = p.getPoints();
        this.period_number = p.getPeriod_number();
    }

    public Period(Score id_score, int points, String period_number) {
        this.id_score = id_score;
        this.points = points;
        this.period_number = period_number;
    }
}
