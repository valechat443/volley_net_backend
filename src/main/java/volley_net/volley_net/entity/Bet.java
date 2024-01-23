package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * scommessa di un utente su chi, secondo lui vincerà una partita
 */
public class Bet {

    /**
     * identificativo di bet
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_bet;

    /**
     * {@link User} che ha fatto la bet
     */
    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private User id_user;

    /**
     * {@link Game} su cui si scommette
     */
    @ManyToOne
    @JoinColumn(name="id_game", nullable = false)
    private Game id_game;

    /**
     * {@link Team} che l'utente crede che vincerà
     */
    @ManyToOne
    @JoinColumn(name="id_team", nullable = false)
    private Team id_team;

    @Column()
    private Boolean check;

    /**
     * costruttore per creare una bet senza un id pre impostato
     * @param id_game oggetto {@link Game}
     * @param id_user oggetto {@link User}
     * @param id_team oggetto {@link Team}
     */
    public Bet(Game id_game, User id_user, Team id_team,Boolean check) {
        this.id_game = id_game;
        this.id_user = id_user;
        this.id_team = id_team;
        this.check=check;
    }

    /**
     * costruttore che crea un oggetto bet partendo da uno già esistente
     * @param b oggetto bet
     */
    public Bet(Bet b) {
        this.id_bet = getId_bet();
        this.id_game = b.getId_game();
        this.id_user = b.getId_user();
        this.id_team = b.getId_team();
        this.check=b.getCheck();
    }
}
