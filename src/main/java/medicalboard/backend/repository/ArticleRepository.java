package medicalboard.backend.repository;

import lombok.NonNull;
import medicalboard.backend.mapper.ArticleMapper;
import medicalboard.backend.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    @Query("SELECT a FROM Article a WHERE a.title LIKE %:keyword% OR a.content LIKE %:keyword%")
    List<Article> findByKeyword(@Param("keyword") String keyword);

    @Query("SELECT a.id AS id, a.hashtag AS hashtag FROM Article a")
    List<ArticleMapper> findHashtag();
    Optional<Article> findById(@NonNull Integer id);
}
