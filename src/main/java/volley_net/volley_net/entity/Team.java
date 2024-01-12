package volley_net.volley_net.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Team {
    @Id
    @EqualsAndHashCode.Include
    private int id_team;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String logo;

    @Column(nullable = false)
    private boolean national=false;

    public Team(Team t) {
        this.id_team = t.getId_team();
        this.name = t.getName();
        this.logo = t.getLogo();
        this.national = t.isNational();
    }

    public Team(int id_team) {
        this.id_team = id_team;
    }
}
