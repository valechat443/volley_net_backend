package volley_net.volley_net.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import volley_net.volley_net.entity.User;
import volley_net.volley_net.service.UserToken;

public interface UserRepository extends JpaRepository<User,Integer> {


    User findByUsername(String username);

    @Query(value = "SELECT new volley_net.volley_net.entity.User(u) " +
            " FROM User u WHERE u.id_user=:id_user")
    User getUserById(@Param("id_user") int id_user);

    @Query(value = "SELECT new volley_net.volley_net.entity.User(u) " +
            " FROM User u WHERE u.mail=:mail")
    User getUserByMail(@Param("mail") String mail);

    @Query(value = "SELECT u.money " +
            " FROM User u WHERE u.id_user=:id_user")
    int getMoneyById(@Param("id_user") int id_user);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user u " +
            "SET u.money=u.money+:num " +
            "WHERE u.id_user=:id_user", nativeQuery = true)
    void updateMoney(@Param("num") int num, @Param("id_user") int id_user);


}