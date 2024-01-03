package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team_list {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_team_list;

    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private User id_user;

    @ManyToOne
    @JoinColumn(name="id_team_season", nullable = false)
    private Team_season id_team_season;

    @ManyToOne
    @JoinColumn(name="id_group", nullable = false)
    private Group id_group;

    public Team_list(User id_user, Team_season id_team_season, Group id_group) {
        this.id_user = id_user;
        this.id_team_season = id_team_season;
        this.id_group = id_group;
    }
}
