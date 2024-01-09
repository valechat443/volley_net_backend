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
    private Integer sets;

    public Score(Score s) {
        this.id_score = s.getId_score();
        this.id_game = s.getId_game();
        this.id_team = s.getId_team();
        this.home = s.isHome();
        this.sets = s.getSets();
    }

    public Score(Game id_game, Team id_team, boolean home, Integer sets) {
        this.id_game = id_game;
        this.id_team = id_team;
        this.home = home;
        this.sets = sets;
    }
}
