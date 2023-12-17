package volley_net.volley_net.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_user;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean verified=false;

    @Column(nullable = false)
    private boolean admin=false;

    @Column(nullable = false)
    private int money=0;

    public User(String mail, String username, String encryptedPassword, boolean admin) {
        this.mail=mail;
        this.username=username;
        this.password=encryptedPassword;
        this.admin=admin;

    }
}
