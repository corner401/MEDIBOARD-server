package medicalboard.backend.dataController;

import medicalboard.backend.DTO.StatDetailDTO;
import medicalboard.backend.model.Article;
import medicalboard.backend.entity.Statistics;
import medicalboard.backend.repository.ArticleMapping;
import medicalboard.backend.repository.ArticleRepository;
import medicalboard.backend.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class StatDetailController {
    @Autowired
    StatisticsRepository statisticsRepository;
    @Autowired
    ArticleRepository articleRepository;



    @GetMapping("/api/stat/detail")
    public StatDetailDTO getDetail(@RequestParam("id") Integer id){

        //id에 따른 Statistics 가져오기
        // id = front에서 전달
        //id를 가진 statistic get
        Optional<Statistics> statistic = statisticsRepository.findById(id);
        //Statistics의 hashtags
        List<String> hashtags = statistic.map(s -> List.of(s.getHashtag().split(","))).orElse(Collections.emptyList());
        //모든 기사의 hashtags
        List<ArticleMapping> articleMapping = articleRepository.findHashtag();

        //Article List
        List<Article> article = new ArrayList<>();
        for( String text : hashtags ){ //통계 hashtags
            for(ArticleMapping mapping : articleMapping) //기사 hashtag
                if(text.equals(mapping.getHashtag())){
                Article emptyArticle = new Article();
                Article getArti = articleRepository.findById(mapping.getId()).orElse(emptyArticle);
                article.add(getArti);
            }
        }

        StatDetailDTO statDetailDTO = new StatDetailDTO(statistic, article);

        return statDetailDTO;
    }
}
