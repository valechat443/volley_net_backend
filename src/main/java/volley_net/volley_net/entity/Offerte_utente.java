package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Offerte_utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_offerta;

    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private User id_user;

    @Column(nullable = false)
    private String name_offerta;

    public Offerte_utente(Offerte_utente of) {
        this.id_offerta = of.id_offerta;
        this.id_user = of.getId_user();
        this.name_offerta = of.getName_offerta();
    }

    public Offerte_utente(User id_user, String name_offerta) {
        this.id_user = id_user;
        this.name_offerta = name_offerta;
    }
}
