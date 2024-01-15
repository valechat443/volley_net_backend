package volley_net.volley_net.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * scommessa di un utente su chi, secondo lui vincera una partita
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
     * utente che ha fatto la bet
     */
    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private User id_user;

    /**
     * game su cui si scomette
     */
    @ManyToOne
    @JoinColumn(name="id_game", nullable = false)
    private Game id_game;

    /**
     * team che l'utente crede che vincerà
     */
    @ManyToOne
    @JoinColumn(name="id_team", nullable = false)
    private Team id_team;

    /**
     * costruttore per creare una bet senza un id pre impostato
     * @param id_game
     * @param id_user
     * @param id_team
     */
    public Bet(Game id_game, User id_user, Team id_team) {
        this.id_game = id_game;
        this.id_user = id_user;
        this.id_team = id_team;
    }

    /**
     * costruttore che crea un oggetto bet partendo da uno già esistente
     * @param b
     */
    public Bet(Bet b) {
        this.id_bet = getId_bet();
        this.id_game = b.getId_game();
        this.id_user = b.getId_user();
        this.id_team = b.getId_team();
    }
}
