package medicalboard.backend.repository;

import lombok.NonNull;
import medicalboard.backend.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface StatisticsRepository extends JpaRepository<Statistics, Integer> {

    Statistics getById(Integer id);

//    Statistics findByKeyword(String);
    List<Statistics> findByHashtag(String hashtag);
    @Query("SELECT s.title AS title, s.hashtag AS hashtag FROM Statistics s")
    List<StatisticsMapping> findHashtag();
    Statistics findByTitle(String title);
    @Query("SELECT s FROM Statistics s WHERE s.title LIKE %:keyword% OR s.summary LIKE %:keyword% OR s.content LIKE %:keyword%")
    List<Statistics> findByKeyword(@Param("keyword") String keyword);

    Optional<Statistics> findById(Integer id);

}

