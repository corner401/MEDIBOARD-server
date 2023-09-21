package medicalboard.backend.dataController;
import medicalboard.backend.DTO.ArticleDTO;
import medicalboard.backend.entity.Article;
import medicalboard.backend.repository.ArticleRepository;
import medicalboard.backend.service.ArticleService;
import org.checkerframework.checker.index.qual.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    /*
    뉴스 기사 크롤링
     */
    @GetMapping("/scrape") //http://localhost:8081/scrape?searchWord=의약
    public String scrapeData(@RequestParam("searchWord") String searchWord, Model model) throws IOException {
        List<Article> articles = new ArrayList<>();
        int result=0;
        int id = 0;
        try {
            //searchWord로 url연결
            String url = "https://www.dailymedi.com/news/search.php?sch_sca=&sch_sca2=&sch_scope=&sch_scope2=&sch_scope3=&sdate=&edate=&stx=" + searchWord;
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .get();
            //첫번째 페이지에 나오는 마지막 페이지
            Elements total = doc.select("li.page-item");
            //마지막 페이지 url로 연결
            if(total.size() != 0){
                String lastUrl = "https://www.dailymedi.com/" + total.get(total.size()-1).select("a.page-link").attr("href");
                doc = Jsoup.connect(lastUrl)
                        .userAgent("Mozilla/5.0")
                        .get();
            }

            //마지막 페이지 수
            String lastpage = doc.select("span.page-link").text();

            //전체 페이지 탐색
            for (int page=1; page<=Integer.parseInt(lastpage); page++) {

                //url에 페이지 번호 추가 + url 연결
                url += "&sop=&page=" + page;
                doc = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0")
                        .get();

                //기사 가져오기
                Elements articleElements = doc.select("ul.sch_res_list > li");
                for (Element articleElement : articleElements) {
                    //제목
                    String title = articleElement.select("a.sch_res_title").text();
                    //url
                    String articleUrl = "https://www.dailymedi.com/" + articleElement.select("a.sch_res_title").attr("href");

                    // 기사 상세페이지
                    Document articlePage = Jsoup.connect(articleUrl).get();
                    //날짜
                    Elements dateElements = articlePage.select("div.info > span");
                    String date = dateElements.get(0).text()+":00";
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                    Date createdDate = formatter.parse(date);

                    //<div>
                    Element parentElement = articlePage.selectFirst("div.arti");
                    //자식태그 <p> or <div>
                    Elements childElements = parentElement.children();
                    //기자이름 제거
                    childElements.remove(childElements.size()-1);
                    //기자이름
                    String writer = childElements.get(childElements.size()-1).text().trim().substring(0,3);
                    //본문 내용 저장소
                    StringBuilder articleContents = new StringBuilder();

                    // 자식 태그들을 순회하며 처리
                    for (Element childElement : childElements) {
                        // 태그 이름 확인
                        String tagName = childElement.tagName();

                        // <p> 태그인지 확인
                        if ("p".equals(tagName)) {
                            articleContents.append(childElement.text().trim());
                        }
                        // <div> 태그인지 확인
                        if("div".equals(tagName)){
                            articleContents.append(childElement.text().trim());
                        }
                    }

                    Article article = new Article();

                    article.setTitle(title);
                    article.setLink(articleUrl);
                    article.setCreateDate(createdDate);
                    article.setUpdateDate(createdDate);
                    article.setContent(articleContents.toString());
                    article.setWriter(writer);
                    article.setNewspaper("데일리 메디");
                    if(searchWord.equals("요양"))
                        searchWord = "요양기관";
                    else if(searchWord.equals("환자"))
                        searchWord = "환자표본";
                    else if(searchWord.equals("병원"))
                        searchWord = "병원평가";
                    else if(searchWord.equals("위치"))
                        searchWord = "위치정보";
                    else if(searchWord.equals("지역"))
                        searchWord = "지역별";
                    else if(searchWord.equals("기간"))
                        searchWord = "기간별";
                    article.setHashtag(searchWord);


                    articleRepository.save(article);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "article-list";
    }

    /*
    뉴스기사 검색
     */
    @GetMapping("/api/news/list")
    //페이지 1번부터
    public Page<ArticleDTO> getArticles(@RequestParam(name = "keyword", required = false) String keyword,
                                     @Positive @RequestParam(name = "page", defaultValue = "1") int page,
                                     @Positive @RequestParam Integer size,
                                     Pageable pageable) {

            page = page - 1;  // 1을 빼서 0부터 시작하도록 조정
            PageRequest pageRequest = PageRequest.of(page, size); //Pageable의 구현체 이면서, 새로운 PageRequest 객체를 생성하며, page와 size 매개변수로 페이지 번호와 페이지 크기를 설정
            Page<Article> articles = articleService.searchArticlesByKeyword(keyword, pageRequest, page, size);
            // articles를 ArticleDTO로 변환하는 로직 필요 (예: 사용할 Mapper를 활용)
            // 예를 들어, `Page.map` 함수를 사용하여 각 Article을 ArticleDTO로 변환할 수 있습니다.
            List<Article> articleList = articles.getContent();

            // 리스트를 DTO로 변환
            List<ArticleDTO> dtoList = articleList.stream()
                    .map(article -> new ArticleDTO(
                            article.getId(),
                            article.getTitle(),
                            article.getWriter(),
                            article.getContent(),
                            article.getLink(),
                            article.getHashtag(),
                            article.getNewspaper()
                    ))
                    .collect(Collectors.toList());

            // 새로운 Page 객체 생성
            Page<ArticleDTO> dtoPage = new PageImpl<>(dtoList, articles.getPageable(), articles.getTotalElements());

            return dtoPage;

    }
}
