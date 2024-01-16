package volley_net.volley_net.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import volley_net.volley_net.entity.User;
import volley_net.volley_net.payload.response.ListUserRankResponse;
import volley_net.volley_net.service.UserToken;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    /**
     * query per trovare l'utente partendo dal''username
     * @param username nome dell'utente
     * @return oggetto user
     */
    User findByUsername(String username);

    /**
     * query per trovare la classifica degli utenti in ordine di scommesse vinte
     * @return lista di user
     */
    @Query(value = "SELECT new volley_net.volley_net.entity.User(u) " +
            "FROM User u " +
            "Order By u.count_bet Desc")
    List<User> getUserRank();

    /**
     * query per trovare l'utente partendo dal suo id
     * @param id_user id dell'utente
     * @return oggetto utente
     */
    @Query(value = "SELECT new volley_net.volley_net.entity.User(u) " +
            " FROM User u WHERE u.id_user=:id_user")
    User getUserById(@Param("id_user") int id_user);

    /**
     * query per trovare l'utente partendo dalla sua mail
     * @param mail mail dell'utente
     * @return oggetto user
     */
    @Query(value = "SELECT new volley_net.volley_net.entity.User(u) " +
            " FROM User u WHERE u.mail=:mail")
    User getUserByMail(@Param("mail") String mail);

    /**
     * query per trovare il bilancio dell'utente
     * @param id_user
     * @return soldi rimasti all'utente
     */
    @Query(value = "SELECT u.money " +
            " FROM User u WHERE u.id_user=:id_user")
    int getMoneyById(@Param("id_user") int id_user);

    /**
     * query per modificare i soldi dell'utente
     * @param num valuta da sommare se positiva, da sottrarre se negativa
     * @param id_user id dell'utente
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE user u " +
            "SET u.money=u.money+:num " +
            "WHERE u.id_user=:id_user", nativeQuery = true)
    void updateMoney(@Param("num") int num, @Param("id_user") int id_user);


}