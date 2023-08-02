package medicalboard.backend.repository;

import io.micrometer.common.lang.NonNullApi;
import lombok.NonNull;
import medicalboard.backend.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface StatisticsRepository extends JpaRepository<Statistics, Integer> {

    Statistics getById(Integer id);

//    Statistics findByKeyword(String);
    List<Statistics> findByHashtag(String hashtag);
    @Query("SELECT s.title AS title, s.hashtag AS hashtag FROM Statistics s")
    List<StatisticsMapping> findHashtag();
    Statistics findByTitle(String title);
    @Query("SELECT s FROM Statistics s WHERE s.table_name LIKE %:keyword% OR s.summary LIKE %:keyword% OR s.title LIKE %:keyword%")
    List<Statistics> findByKeyword(@Param("keyword") String keyword);
    Optional<Statistics> findById(@NonNull Integer id);
}

