package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
/**
 * paese nel mondo
 */
public class Country {

    /**
     * identificativo di country
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_country;

    /**
     * nome del paese
     */
    @Column(nullable=false)
    private String name;

    /**
     * codice identificativo del country
     */
    @Column(length = 2)
    private String code;

    /**
     * link all'immagine della bandiera del country
     */
    @Column()
    private String flag;
}
