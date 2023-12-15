package volley_net.volley_net.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_team;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String logo;

    @Column(nullable = false)
    private boolean nationale=false;
}
