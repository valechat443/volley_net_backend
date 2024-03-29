package volley_net.volley_net.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import volley_net.volley_net.entity.Season;

import java.util.Optional;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Integer> {
    /**
     * query per trovare un oggetto season partendo dall'anno
     * @param year anno
     * @return oggetto {@link Season}
     */
    Optional<Season> findByYear(int year);
}