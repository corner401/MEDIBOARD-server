package medicalboard.backend.mapper;

import lombok.Getter;
import medicalboard.backend.DTO.ArticleDTO;
import medicalboard.backend.entity.Article;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ArticleMapper {

    private String hashtag;
    private Integer id;

    public ArticleDTO toDTO(Article article) {
        return new ArticleDTO(
                article.getId(),
                article.getTitle(),
                article.getWriter(),
                article.getContent(),
                article.getLink(),
                article.getHashtag(),
                article.getNewspaper()
        );
    }

    public List<ArticleDTO> articlesToArticleResponseDtos(List<Article> articles) {
        return articles.stream() //articles.stream()을 호출하여 List<Article>의 스트림을 생성
                .map(this::toDTO) // 스트림 내의 각 Article 객체를 ArticleDTO 객체로 변환
                //this::toDTO는 메소드 레퍼런스로, 현재 클래스(this)의 toDTO 메소드를 가리킵
                .collect(Collectors.toList()); //스트림의 내용을 새로운 List<ArticleDTO>에 담음
    }
}
