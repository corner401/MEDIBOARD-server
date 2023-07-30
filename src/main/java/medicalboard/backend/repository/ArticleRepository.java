package medicalboard.backend.repository;

import medicalboard.backend.model.medicalNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<medicalNews, Integer> {
}
