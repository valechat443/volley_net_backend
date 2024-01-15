package volley_net.volley_net.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
/**
 * utente dell'applicativo
 */
public class User {

    /**
     * identificatore di user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_user;
    /**
     * mail dell'utente
     */
    @Column(nullable = false)
    private String mail;
    /**
     * username dell'utente
     */
    @Column(nullable = false)
    private String username;
    /**
     * password criptata dell'utente
     */
    @Column(nullable = false)
    private String password;
    /**
     * se l'utente è stato verificato
     */
    @Column(nullable = false)
    private boolean verified=false;
    /**
     * se l'utente ha i permesi di admin
     */
    @Column(nullable = false)
    private boolean admin=false;
    /**
     * valuta usabile nell'applicativo che ha l'utente
     */
    @Column(nullable = false)
    private int money=500;
    @Column(length=11,nullable = false)
    private int count_bet=0;

    /**
     * costruttore per creare oggetti user senza un id per creato
     * @param mail
     * @param username
     * @param encryptedPassword
     * @param admin
     */
    public User(String mail, String username, String encryptedPassword, boolean admin) {
        this.mail=mail;
        this.username=username;
        this.password=encryptedPassword;
        this.admin=admin;
        this.money=500;
        this.count_bet=0;
    }

    /**
     * costruttore per creare oggetti user partendo da uno già esistente
     * @param u
     */
    public User(User u) {
        this.id_user = u.getId_user();
        this.mail = u.getMail();
        this.username = u.getUsername();
        this.password = u.getPassword();
        this.verified = u.isVerified();
        this.admin = u.isAdmin();
        this.money = u.getMoney();
        this.count_bet = u.getCount_bet();
    }
}
