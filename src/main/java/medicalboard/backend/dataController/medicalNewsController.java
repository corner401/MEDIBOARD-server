package medicalboard.backend.dataController;
import medicalboard.backend.model.medicalNews;
import medicalboard.backend.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class medicalNewsController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/scrape") //http://localhost:8081/scrape?searchWord=의약
    public String scrapeData(@RequestParam("searchWord") String searchWord, Model model) throws IOException {
        List<medicalNews> articles = new ArrayList<>();
        int result=0;
        int id = 0;
        try {
            //searchWord로 url연결
            String url = "https://www.dailymedi.com/news/search.php?sch_sca=22&sch_sca2=2202&sch_scope=wr_subject&sch_scope2=basic&sch_scope3=&sdate=&edate=&sfl=&stx=" + searchWord;
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .get();
            //첫번째 페이지에 나오는 마지막 페이지
            Elements total = doc.select("li.page-item");
            //마지막 페이지 url로 연결
            String lastUrl = "https://www.dailymedi.com/" + total.get(total.size()-1).select("a.page-link").attr("href");
            doc = Jsoup.connect(lastUrl)
                    .userAgent("Mozilla/5.0")
                    .get();
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
                    Elements dateElements = articlePage.select("div.info > span");
                    String createdDate = dateElements.get(0).text();


                    //본문 ArticleContent -> medicalNews
                    Elements paragraphs = articlePage.select("div.arti > p");
                    System.out.println("paragraph  "+ id +"번째 :"+ paragraphs.getClass()); // 19번째 ~ 27번째 <p> 태그가 없음
                    //본문 내용 articleContents에 모으기
                    StringBuilder articleContents = new StringBuilder();
                    for (Element paragraphElement : paragraphs) {
                        articleContents.append(paragraphElement.text());
                    }

                    medicalNews article = new medicalNews();
                    article.setTitle(title);
                    article.setUrl(articleUrl);
                    article.setCreatedDate(createdDate);
                    article.setContentList(articleContents.toString());
                    System.out.println("content" + articleContents);
                    article.setHashtag(searchWord);

                    articleRepository.saveAndFlush(article);

                    articles.add(article); // Add the scraped article to the list
                    id++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("articles", articles);
        return "article-list"; // Thymeleaf template name for rendering the article list
    }
}
