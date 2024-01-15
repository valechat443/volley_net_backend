package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * giocatore di pallavolo
 */
public class Player {
    /**
     * identificatore di player
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_player;

    /**
     * team di cui il player fa parte
     */
    @ManyToOne
    @JoinColumn(name="id_team", nullable = false)
    private Team id_team;

    /**
     * ruolo del player
     */
    @Column()
    private String ruolo;

    /**
     * nome del player
     */
    @Column()
    private String nome;
    /**
     * cognome del player
     */
    @Column()
    private String cognome;

}
