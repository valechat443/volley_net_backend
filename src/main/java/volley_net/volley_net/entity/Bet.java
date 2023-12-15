package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_bet;

    @ManyToOne
    @JoinColumn(name="id_game", nullable = false)
    private Game id_game;

    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private User id_user;

    @ManyToOne
    @JoinColumn(name="id_team", nullable = false)
    private Team id_team;
}
