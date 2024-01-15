package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * previsione che fa un utente per vedere se il team scelto arriverà in podio nel suo gruppo
 */
public class Team_list {
    /**
     * identificatore di team_list
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_team_list;

    /**
     * utente che fa la scomessa
     */
    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private User id_user;

    /**
     * mix di team, season e league
     */
    @ManyToOne
    @JoinColumn(name="id_team_season", nullable = false)
    private Team_season id_team_season;
    /**
     * gruppo a cui il team appartiene in questa lega
     */
    @ManyToOne
    @JoinColumn(name="id_group", nullable = false)
    private Group id_group;

    /**
     * costruttore per creare oggetti team_list senza un id per creato
     * @param id_user
     * @param id_team_season
     * @param id_group
     */
    public Team_list(User id_user, Team_season id_team_season, Group id_group) {
        this.id_user = id_user;
        this.id_team_season = id_team_season;
        this.id_group = id_group;
    }
}
