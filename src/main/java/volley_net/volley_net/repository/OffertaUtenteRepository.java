package volley_net.volley_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import volley_net.volley_net.entity.Offerte_utente;

import java.util.List;

public interface OffertaUtenteRepository  extends JpaRepository<Offerte_utente,Integer> {


    /**
     * query per trovare la lista di offerte acquistate da un utente
     * @param id_user id dell'utente
     * @return la lista di offerte
     */

    @Query(value = "SELECT  new volley_net.volley_net.entity.Offerte_utente(of) " +
            "from Offerte_utente of " +
            "where of.id_user.id_user=:id_user")
    List<Offerte_utente> getListaOfferte(@Param("id_user") int id_user);
}
