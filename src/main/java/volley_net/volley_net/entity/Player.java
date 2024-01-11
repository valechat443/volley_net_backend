package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_player;

    @ManyToOne
    @JoinColumn(name="id_team", nullable = false)
    private Team id_team;

    @Column()
    private String ruolo;

    @Column()
    private String nome;

    @Column()
    private String cognome;

}
