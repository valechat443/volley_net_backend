package volley_net.volley_net.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_group;

    @ManyToOne
    @JoinColumn(name="id_season", nullable = false)
    private Season id_season;

    @ManyToOne
    @JoinColumn(name="id_league", nullable = false)
    private League id_league;

    @Column(nullable = false)
    private String group_name;
}
