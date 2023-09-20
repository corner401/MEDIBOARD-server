package medicalboard.backend.service;

import medicalboard.backend.entity.Article;
import medicalboard.backend.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    //전체 뉴스 리스트 pagenation
    public Page<Article> findArticle(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size); //page 개수와 size(page 별 데이터 개수)를 매개변수로 받아 PageRequest 객체를 생성
        return articleRepository.findAllByOrderByIdDesc(pageRequest); // repository로 반환
    }

    //키워드 + pagenation
    public Page<Article> searchArticlesByKeyword(String keyword, Pageable pageable, int page, int size){
        if(keyword == null){
            PageRequest pageRequest = PageRequest.of(page, size); //page 개수와 size(page 별 데이터 개수)를 매개변수로 받아 PageRequest 객체를 생성
            return articleRepository.findAllByOrderByIdDesc(pageRequest); // repository로 반환
        }
        else {
            return articleRepository.findByKeyword(keyword, pageable);
        }
    }
}
