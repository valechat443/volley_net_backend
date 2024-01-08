package volley_net.volley_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import volley_net.volley_net.entity.Score;

public interface ScoreRepository extends JpaRepository<Score,Integer> {
}
