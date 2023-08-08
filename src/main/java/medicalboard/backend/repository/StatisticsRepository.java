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
    @Query("SELECT s FROM Statistics s WHERE s.tableName LIKE %:keyword% OR s.summary LIKE %:keyword% OR s.title LIKE %:keyword%")
    List<Statistics> findByKeyword(@Param("keyword") String keyword);

    @Query("SELECT s.id AS id FROM Statistics s")
    Optional<Statistics> findById(Integer id);

}

