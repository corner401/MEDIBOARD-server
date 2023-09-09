package medicalboard.backend.repository;

import lombok.NonNull;
import medicalboard.backend.mapper.ArticleMapper;
import medicalboard.backend.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    //키워드 + pagenation
    @Query("SELECT a FROM Article a WHERE a.title LIKE %:keyword% OR a.content LIKE %:keyword%")
    Page<Article> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT a.id AS id, a.hashtag AS hashtag FROM Article a")
    List<ArticleMapper> findHashtag();
    Optional<Article> findById(@NonNull Integer id);

    //전체뉴스 리스트 pagenation
    Page<Article> findAllByOrderByIdDesc(Pageable pageable); //Article id 기준으로 내림차순으로(최신순) 모든 정보를 가져온다.

}
