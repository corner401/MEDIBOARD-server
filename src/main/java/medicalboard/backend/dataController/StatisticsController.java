package medicalboard.backend.dataController;

import medicalboard.backend.model.Article;
import medicalboard.backend.model.Emergency;
import medicalboard.backend.model.Statistics;
import medicalboard.backend.repository.ArticleRepository;
import medicalboard.backend.repository.EmergencyRepository;
import medicalboard.backend.repository.StatisticsRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class StatisticsController {

    @Autowired
    StatisticsRepository statisticsRepository;

    @GetMapping("/api/stat")
    public String statistics_search(Model model) throws IOException, ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String source1 = "공공데이터포탈";
        String source2 = "HIRD 빅데이터 개방포털";

        //Emergency
        Statistics statistics = new Statistics(format.parse("2017-03-16"), format.parse("2023-07-04"), "응급실 실시간 가용병상정보 조회",
                "Emergency","응급실 실시간 가용병상정보 조회 오퍼레이션 명세" , "주소를 기준으로 응급실 실시간 가용병상정보 등을 조회하는 응급실 실시간 가용병상정보 조회 기능제공",
                source1, "응급, 실시간");

        statisticsRepository.save(statistics);

        //Article
        Statistics statistics1 = new Statistics(format.parse("2023-07-30"),format.parse("2023-07-30"), "뉴스 기사",
                "Article", "뉴스 기사 클롤링", "카테고리별 뉴스기사 크롤링 테이블", "데일리 메디", "의약,의원");
        statisticsRepository.save(statistics1);

        //Hospital
        Statistics statistics2 = new Statistics(format.parse("2021-01-08"),format.parse("2023-07-17"), "전국 병원 정보"
                ,"Hospital","병원 정보 서비스", "지역, 종별코드명, 요양기관명", source2, "병원,위치");
        statisticsRepository.save(statistics2);

        //MedicalSupplies
        Statistics statistics3 = new Statistics(format.parse("2023-02-14"),format.parse("2023-05-01"), "요양기관소재지별/병상규모별 의약품 사용 통계",
                "MedicalSupplies", "기간별, 지역별, 병상 규모별 의약 수량, 사용금액", "기간명, 지역코드, 지역명, 병상규모별, 수량, 사용금액", source2,"지역,병상규모,의약");
        statisticsRepository.save(statistics3);

        //MedicalHospital
        Statistics statistics4 = new Statistics(format.parse("2023-02-14"), format.parse("2023-05-01"), "요양기관소재지별/요양기관종별 의약품 사용 통계",
                "MedicalHospitalSupplies", "기간별, 지역별, 요양기관종별 의약 수량, 사용금액", "기간명, 지역코드, 지역명, 요양기관종별, 수량, 사용금액", source2,"지역,요양기관종,의약" );
        statisticsRepository.save(statistics4);

        //Pharmacy
        Statistics statistics5 = new Statistics(format.parse("2021-01-08"),format.parse("2023-07-17"), "전국 약국 정보",
                "Pharmacy", "전국 약국 정보 서비스", "요양기관명, 종별코드, 종별코드명, 시도코드, 시도코드명, 시도군구코드, 시도군구코드명, 우편번호, 주소, 전화번호, 위치",source2, "약국, 위치");
        statisticsRepository.save(statistics5);

//        model.addAttribute("articles", articles);
        return "statistics-list";
    }

}
