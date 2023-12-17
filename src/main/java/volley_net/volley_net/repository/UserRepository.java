package volley_net.volley_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import volley_net.volley_net.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {


    User findByUsername(String username);

    @Query(value = "SELECT new volley_net.volley_net.entity.User(" +
            "u.id_user," +
            "u.password)" +

            " FROM User u WHERE u.id_user=:id_user")
    User getUserById(@Param("id_user") int id_user);

}