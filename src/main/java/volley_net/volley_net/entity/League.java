package volley_net.volley_net.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * singola lega di palavolo
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
     * paese in cui la lega si svolge
     */
    @ManyToOne
    @JoinColumn(name="id_country", nullable = false)
    private Country id_country;

    /**
     * costruttore per creare un oggetto group con solo l'id_league
     * @param id
     */
    public League(int id) {
        this.id_league=id;
    }
}
