package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Standing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_standing;

    @ManyToOne
    @JoinColumn(name="id_team_season", nullable = false)
    private Team_season id_team_season;

    @ManyToOne
    @JoinColumn(name="id_group", nullable = false)
    private Group id_group;

    @Column(nullable = false)
    private int position;

    @Column(nullable = false)
    private int points;

    @Column(length = 5,nullable = true)
    private String form;

    @Column(nullable = true)
    private String zona;

    public Standing(Standing s) {
        this.id_standing = s.getId_standing();
        this.id_team_season = s.getId_team_season();
        this.id_group = s.getId_group();
        this.position = s.getPosition();
        this.points = s.getPoints();
        this.form = s.getForm();
        this.zona = s.getZona();
    }
}
