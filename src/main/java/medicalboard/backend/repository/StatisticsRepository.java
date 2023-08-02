package medicalboard.backend.repository;

import medicalboard.backend.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Integer> {

    Statistics getById(Integer id);
}
