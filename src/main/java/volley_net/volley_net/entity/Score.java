package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_score;

    @ManyToOne
    @JoinColumn(name="id_game", nullable = false)
    private Game id_game;

    @ManyToOne
    @JoinColumn(name="id_team", nullable = false)
    private Team id_team;

    @Column(nullable = false)
    private boolean home;

    @Column(nullable = true)
    private int sets;
}
