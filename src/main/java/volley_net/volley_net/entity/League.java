package volley_net.volley_net.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * singola lega di pallavolo
 */
public class League {

    /**
     * identificativo di lega
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_league;
    /**
     * nome della lega
     */
    @Column(nullable = false)
    private String name;

    /**
     * tipo della lega
     */
    @Column(nullable = false)
    private String type;

    /**
     * link all'immagine del logo del team
     */
    @Column(nullable = false)
    private String logo;

    /**
     * {@link League} in cui la lega si svolge
     */
    @ManyToOne
    @JoinColumn(name="id_country", nullable = false)
    private Country id_country;

    /**
     * costruttore per creare un oggetto group con solo l'id_league
     * @param id identificativo di lega
     */
    public League(int id) {
        this.id_league=id;
    }

    /**
     * costruttore per creare oggetti League partendo dda un oggetto esistente
     * @param l oggetto lega
     */
    public League(League l) {
        this.id_league = l.getId_league();
        this.name = l.getName();
        this.type = l.getType();
        this.logo = l.getLogo();
        this.id_country = l.getId_country();
    }
}
