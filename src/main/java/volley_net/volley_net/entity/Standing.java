package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * classifica dei team in un gruppo, in una lega, in una stagione
 */
public class Standing {

    /**
     * identificatore di standing
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_standing;

    /**
     * {@link Team_season} mix di {@link Season}, {@link  League} e {@link  Team}, da usare come chiave
     */
    @ManyToOne
    @JoinColumn(name="id_team_season", nullable = false)
    private Team_season id_team_season;

    /**
     * {@link  Group} della classifica
     */
    @ManyToOne
    @JoinColumn(name="id_group", nullable = false)
    private Group id_group;

    /**
     * posizione in classifica del team
     */
    @Column(nullable = false)
    private int position;
    /**
     * punti che il team ha guadagnato
     */
    @Column(nullable = false)
    private int points;
    /**
     * stringa che rappresenta i game vinti e persi nella lega
     */
    @Column(length = 5,nullable = true)
    private String form;

    /**
     * zona di elimination della lega in cui il team si trova
     */
    @Column(nullable = true)
    private String zona;

    /**
     * costruttore che crea un oggetto standing partendo da uno esistente
     * @param s oggetto standing
     */
    public Standing(Standing s) {
        this.id_standing = s.getId_standing();
        this.id_team_season = s.getId_team_season();
        this.id_group = s.getId_group();
        this.position = s.getPosition();
        this.points = s.getPoints();
        this.form = s.getForm();
        this.zona = s.getZona();
    }

    /**
     * costruttore che crea un oggetto group con solo l'id_group
     * @param id_group identificativo di {@link Group}
     */
    public Standing(Group id_group) {
        this.id_group = id_group;
    }

    /**
     * costruttore per creare oggetti standing senza un id pre creato
     * @param id_team_season identificativo di {@link  Team_season}
     * @param id_group identificativo di {@link Group}
     * @param position posizione del team
     * @param points punti del team
     * @param form ultime vittorie/sconfitte
     * @param zona zona di eliminazione del torneo
     */
    public Standing(Team_season id_team_season, Group id_group, int position, int points, String form, String zona) {
        this.id_team_season = id_team_season;
        this.id_group = id_group;
        this.position = position;
        this.points = points;
        this.form = form;
        this.zona = zona;
    }
}
