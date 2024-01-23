package volley_net.volley_net.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
/**
 * team che gioca a pallavolo
 */
public class Team {
    /**
     * identificatore di team
     */
    @Id
    @EqualsAndHashCode.Include
    private int id_team;

    /**
     * nome del team
     */
    @Column(nullable = false)
    private String name;
    /**
     * link al logo del team
     */
    @Column(nullable = false)
    private String logo;
    /**
     * se il team è nazionale
     */
    @Column(nullable = false)
    private boolean national=false;

    /**
     * costruttore
     * @param t oggetto team
     */
    public Team(Team t) {
        this.id_team = t.getId_team();
        this.name = t.getName();
        this.logo = t.getLogo();
        this.national = t.isNational();
    }

    /**
     * costruttore di oggetti team con solo l'id_team
     * @param id_team
     */
    public Team(int id_team) {
        this.id_team = id_team;
    }
}
