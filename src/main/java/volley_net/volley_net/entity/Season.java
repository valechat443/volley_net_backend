package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode @ToString
/**
 * hanno delle stagioni competitive
 */
public class Season {

    /**
     * identificativo di season
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_season;

    /**
     * anno della season
     */
    @Column(nullable = false)
    private int year;

    /**
     *   costruttore completo di oggetti season
     */
    public Season(int id_season, int year){
        this.id_season = id_season;
        this.year=year;
    }

    /**
     * costruttore che crea un oggetto season con solo l'id_season
     * @param id_season identificatore di season
     */
    public Season(int id_season) {
        this.id_season = id_season;
    }
}
