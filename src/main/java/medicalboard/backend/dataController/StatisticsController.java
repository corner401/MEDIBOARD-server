package medicalboard.backend.dataController;

import medicalboard.backend.model.Statistics;
import medicalboard.backend.repository.StatisticsMapping;
import medicalboard.backend.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StatisticsController {

    @Autowired
    StatisticsRepository statisticsRepository;
    /*
    데이터 내용 DB에 저장
     */
    @GetMapping("/api/stat/save")
    public String statistics_search(Model model) throws IOException, ParseException {
        String hashtag1 = "의약품";
        String hashtag2 = "요양기관";
        String hashtag3 = "진료정보";
        String hashtag4 = "환자표본";
        String hashtag5 = "병원평가";
        String hashtag6 = "응급";
        String hashtag7 = "의료자원";
        String hashtag8 = "지역별";
        String hashtag9 = "위치정보";
        String hashtag10 = "실시간";
        String hashtag11 = "기간별";
        String hashtag12 = "약국";


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String source1 = "공공데이터포탈";
        String source2 = "HIRD 빅데이터 개방포털";

        //Emergency
        Statistics statistics = new Statistics(format.parse("2017-03-16"), format.parse("2023-07-04"), "응급실 실시간 가용병상정보 조회",
                "Emergency", "응급실 실시간 가용병상정보 조회 오퍼레이션 명세", "주소를 기준으로 응급실 실시간 가용병상정보 등을 조회하는 응급실 실시간 가용병상정보 조회 기능제공",
                source1, hashtag6+","+hashtag10);

        statisticsRepository.save(statistics);

        //Article
        Statistics statistics1 = new Statistics(format.parse("2023-07-30"), format.parse("2023-07-30"), "뉴스 기사",
                "Article", "뉴스 기사 클롤링", "카테고리별 뉴스기사 크롤링 테이블", "데일리 메디", hashtag1+","+hashtag2);
        statisticsRepository.save(statistics1);

        //Hospital
        Statistics statistics2 = new Statistics(format.parse("2021-01-08"), format.parse("2023-07-17"), "전국 병원 정보"
                , "Hospital", "병원 정보 서비스", "지역, 종별코드명, 요양기관명", source2, hashtag2+","+hashtag9+","+hashtag7+","+hashtag9+","+hashtag8);
        statisticsRepository.save(statistics2);

        //MedicalSupplies
        Statistics statistics3 = new Statistics(format.parse("2023-02-14"), format.parse("2023-05-01"), "요양기관소재지별/병상규모별 의약품 사용 통계",
                "MedicalSupplies", "기간별, 지역별, 병상 규모별 의약 수량, 사용금액", "기간명, 지역코드, 지역명, 병상규모별, 수량, 사용금액", source2, hashtag8+","+hashtag1+","+hashtag11);
        statisticsRepository.save(statistics3);

        //MedicalHospital
        Statistics statistics4 = new Statistics(format.parse("2023-02-14"), format.parse("2023-05-01"), "요양기관소재지별/요양기관종별 의약품 사용 통계",
                "MedicalHospitalSupplies", "기간별, 지역별, 요양기관종별 의약 수량, 사용금액", "기간명, 지역코드, 지역명, 요양기관종별, 수량, 사용금액", source2, hashtag8+","+hashtag1+","+hashtag2);
        statisticsRepository.save(statistics4);

        //Pharmacy
        Statistics statistics5 = new Statistics(format.parse("2021-01-08"), format.parse("2023-07-17"), "전국 약국 정보",
                "Pharmacy", "전국 약국 정보 서비스", "요양기관명, 종별코드, 종별코드명, 시도코드, 시도코드명, 시도군구코드, 시도군구코드명, 우편번호, 주소, 전화번호, 위치", source2, hashtag12+","+hashtag2+","+hashtag8+","+hashtag9);
        statisticsRepository.save(statistics5);

        return "statistics-list";
    }

    /*
    데이터 리스트 출력
     */
    @GetMapping("/api/stat/list")
    public List<Statistics> getAllStatistics(@RequestParam(name = "keyword", required = false) String keyword,
                                             @RequestParam(name = "hashtag", required = false) String hashtag) {

        if (keyword == null && hashtag == null) {
            return statisticsRepository.findAll();
        } else if (keyword == null) {
            //해시태그 값 가져오기
            List<StatisticsMapping> hashtagList = statisticsRepository.findHashtag();
            //모든 테이블의 해시태그 가져오기
            List<Statistics> stat = new ArrayList<>();
            for (StatisticsMapping mapping : hashtagList) {
                //해시태그를 가져와서 , 으로 분리(String List)
                List<String> hashtags = List.of(mapping.getHashtag().split(","));
                for(String text : hashtags){ //모든 hashtag
                    if(text.equals(hashtag)){ //만약 선택한 hashtag와 일치한다면
                        //Title에 맞는 Statistics 가져오기
                        Statistics getStatics = statisticsRepository.findByTitle(mapping.getTitle());
                        stat.add(getStatics);
                    }
                }
            }
            return stat;
        } else if(hashtag == null){
            return statisticsRepository.findByKeyword(keyword);
        }
        return null;
    }
}
