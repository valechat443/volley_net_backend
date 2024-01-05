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


    @Column(nullable = false)
    private String group_name;

    public Group(String group_name) {
        this.group_name = group_name;
    }

    public Group(Group g) {
        this.id_group = g.getId_group();
        this.group_name = g.group_name;
    }
}
