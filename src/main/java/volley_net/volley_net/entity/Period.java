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

    private int points;

    @Column(length = 6)
    private String period_number;
}
