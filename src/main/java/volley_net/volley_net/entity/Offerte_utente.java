package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * offerta acquistata da un utente
 */
public class Offerte_utente {

    /**
     * identificativo dell'offerta
      */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_offerta;

    /**
     * {@link  User} che ha fatto l'acquisto
     */
    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private User id_user;

    /**
     * descrizione dell'offerta utente
     */
    @Column(nullable = false)
    private String name_offerta;

    /**
     * costruttore che crea un nuovo oggetto offerta_utente partendo da un altro esistente
     * @param of oggetto
     */
    public Offerte_utente(Offerte_utente of) {
        this.id_offerta = of.id_offerta;
        this.id_user = of.getId_user();
        this.name_offerta = of.getName_offerta();
    }

    /**
     * costruttore per creare un offerta senza id già impostato
     * @param id_user identificativo di {@link  User}
     * @param name_offerta descrizione dell'offerta
     */
    public Offerte_utente(User id_user, String name_offerta) {
        this.id_user = id_user;
        this.name_offerta = name_offerta;
    }
}
