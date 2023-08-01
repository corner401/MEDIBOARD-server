package medicalboard.backend.dataController;
import medicalboard.backend.model.Article;
import medicalboard.backend.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

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
            String url = "https://www.dailymedi.com/news/search.php?sch_sca=22&sch_sca2=2202&sch_scope=wr_subject&sch_scope2=basic&sch_scope3=&sdate=&edate=&sfl=&stx=" + searchWord;
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
                    article.setCreate_date(createdDate);
                    article.setUpdate_date(createdDate);
                    article.setContent(articleContents.toString());
                    article.setWriter(writer);
                    if(searchWord.equals("요양"))
                        searchWord = "요양기관";
                    article.setHashtag(searchWord);

                    articleRepository.save(article);

//                    articles.add(article); // Add the scraped article to the list
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        model.addAttribute("articles", articles);
        return "article-list"; // Thymeleaf template name for rendering the article list
    }

}
