package medicalboard.backend.dataController;

import medicalboard.backend.entity.Statistics;
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
import java.util.Arrays;
import java.util.List;

@RestController
public class StatisticsController {

    @Autowired
    StatisticsRepository statisticsRepository;
    /*
    데이터 내용 DB에 저장
     */
    @GetMapping("/api/stat/save")
    public String statistics_search(Model model) throws IOException, ParseException {

        List<String> hashtagList = new ArrayList<String>(Arrays.asList("의약품","요양기관","진료정보","환자표본","병원평가","응급","의료자원","지역별","위치정보","실시간","기간별","약국"));



        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String source1 = "공공데이터포탈";
        String source2 = "HIRD 빅데이터 개방포털";

        //Emergency
        Statistics statistics = new Statistics("","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&autoAuth=true&ctid=fd09b2bc-9220-4c6b-8372-220b7bd51819","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly9XQUJJLUVBU1QtQVNJQS1BLVBSSU1BUlktcmVkaXJlY3QuYW5hbHlzaXMud2luZG93cy5uZXQiLCJlbWJlZEZlYXR1cmVzIjp7Im1vZGVybkVtYmVkIjp0cnVlLCJ1c2FnZU1ldHJpY3NWTmV4dCI6dHJ1ZX19"
                ,"498b79cc-dfc2-4f7d-b158-f993568b2e02",format.parse("2017-03-16"), format.parse("2023-07-04"), "응급병원 지역별 활관촬영기 가용",
                "Emergency", "응급병원 지역별 활관촬영기 가용", "지역별,혈관 촬영기 개수",
                source1, hashtagList.get(5)+","+hashtagList.get(9)+","+hashtagList.get(6)+","+hashtagList.get(7));

        statisticsRepository.save(statistics);

        //Pharmacy
        Statistics statistics1 = new Statistics("","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&autoAuth=true&ctid=fd09b2bc-9220-4c6b-8372-220b7bd51819","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly9XQUJJLUVBU1QtQVNJQS1BLVBSSU1BUlktcmVkaXJlY3QuYW5hbHlzaXMud2luZG93cy5uZXQiLCJlbWJlZEZlYXR1cmVzIjp7Im1vZGVybkVtYmVkIjp0cnVlLCJ1c2FnZU1ldHJpY3NWTmV4dCI6dHJ1ZX19",
                "498b79cc-dfc2-4f7d-b158-f993568b2e02",format.parse("2021-01-08"), format.parse("2023-07-17"), "전국 약국 정보",
                "Pharmacy", "전국 약국 정보 서비스", "요양기관명, 종별코드, 종별코드명, 시도코드, 시도코드명, 시도군구코드, 시도군구코드명, 우편번호, 주소, 전화번호, 위치", source2, hashtagList.get(11)+","+hashtagList.get(1)+","+hashtagList.get(7)+","+hashtagList.get(8));
        statisticsRepository.save(statistics1);

        //시군별/ 종별 총 의사
        Statistics statistics2 = new Statistics("","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&autoAuth=true&ctid=fd09b2bc-9220-4c6b-8372-220b7bd51819","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly9XQUJJLUVBU1QtQVNJQS1BLVBSSU1BUlktcmVkaXJlY3QuYW5hbHlzaXMud2luZG93cy5uZXQiLCJlbWJlZEZlYXR1cmVzIjp7Im1vZGVybkVtYmVkIjp0cnVlLCJ1c2FnZU1ldHJpY3NWTmV4dCI6dHJ1ZX19",
                "498b79cc-dfc2-4f7d-b158-f993568b2e02",  format.parse("2021-01-08"), format.parse("2023-07-17"), "시군별/종별 총 의사 수", "Hospital", "시군별/종별 총 의사 수", "시군구코드명, 합계 총의사수개, 종별코드명", source1,
                hashtagList.get(1)+","+hashtagList.get(6)+","+hashtagList.get(7));
        statisticsRepository.save(statistics2);

        //지역별 총 의사수와 외과 전문의 수
        Statistics statistics3 = new Statistics("","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&autoAuth=true&ctid=fd09b2bc-9220-4c6b-8372-220b7bd51819","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly9XQUJJLUVBU1QtQVNJQS1BLVBSSU1BUlktcmVkaXJlY3QuYW5hbHlzaXMud2luZG93cy5uZXQiLCJlbWJlZEZlYXR1cmVzIjp7Im1vZGVybkVtYmVkIjp0cnVlLCJ1c2FnZU1ldHJpY3NWTmV4dCI6dHJ1ZX19"
        ,"498b79cc-dfc2-4f7d-b158-f993568b2e02",  format.parse("2021-01-08"), format.parse("2023-07-17"), "지역별 총 의사수와 외과 전문의 수", "Hospital", "지역별 총 의사수와 외과 전문의 수", "시도코드명, 합계 총의사수개, 합계 의과전문의 인원수개", source1,
                hashtagList.get(6)+","+hashtagList.get(7));
        statisticsRepository.save(statistics3);

        //연도별 의약품 수량 및 사용금액
        Statistics statistics4 = new Statistics("","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&autoAuth=true&ctid=fd09b2bc-9220-4c6b-8372-220b7bd51819","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly9XQUJJLUVBU1QtQVNJQS1BLVBSSU1BUlktcmVkaXJlY3QuYW5hbHlzaXMud2luZG93cy5uZXQiLCJlbWJlZEZlYXR1cmVzIjp7Im1vZGVybkVtYmVkIjp0cnVlLCJ1c2FnZU1ldHJpY3NWTmV4dCI6dHJ1ZX19"
        ,"498b79cc-dfc2-4f7d-b158-f993568b2e02", format.parse("2023-02-14"), format.parse("2023-05-01"), "연도별 의약품 수량 및 사용금액", "MedicalSupplies", "연도별 의약품 수량 및 사용금액", "기간명(연도,월), 합계 수량개, 합계 사용금액개", source2,
                hashtagList.get(0)+","+hashtagList.get(6)+","+hashtagList.get(7));

        statisticsRepository.save(statistics4);

        //기간별 의약품 수량 및 사용금액
        Statistics statistics5 = new Statistics("","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&autoAuth=true&ctid=fd09b2bc-9220-4c6b-8372-220b7bd51819","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly9XQUJJLUVBU1QtQVNJQS1BLVBSSU1BUlktcmVkaXJlY3QuYW5hbHlzaXMud2luZG93cy5uZXQiLCJlbWJlZEZlYXR1cmVzIjp7Im1vZGVybkVtYmVkIjp0cnVlLCJ1c2FnZU1ldHJpY3NWTmV4dCI6dHJ1ZX19",
                "498b79cc-dfc2-4f7d-b158-f993568b2e02", format.parse("2023-02-14"), format.parse("2023-05-01"), "기간별 의약품 수량 및 사용금액", "MedicalHospital", "기간별 의약품 수량 및 사용금액", "기간명(연도, 월), 합계 수량개, 합계 사용금액개", source2,
                hashtagList.get(0)+","+hashtagList.get(6)+","+hashtagList.get(9));
        statisticsRepository.save(statistics5);

        //요양기간별 의약품 수 및 사용금액
        Statistics statistics6 = new Statistics("","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&autoAuth=true&ctid=fd09b2bc-9220-4c6b-8372-220b7bd51819","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly9XQUJJLUVBU1QtQVNJQS1BLVBSSU1BUlktcmVkaXJlY3QuYW5hbHlzaXMud2luZG93cy5uZXQiLCJlbWJlZEZlYXR1cmVzIjp7Im1vZGVybkVtYmVkIjp0cnVlLCJ1c2FnZU1ldHJpY3NWTmV4dCI6dHJ1ZX19",
                "498b79cc-dfc2-4f7d-b158-f993568b2e02", format.parse("2023-02-14"), format.parse("2023-05-01"),"요양기간별 의약품 수 및 사용금액", "MedicalHospital", "요양기간별 의약품 수 및 사용금액", "요양기관종별, 합계 수량개, 합계 사용금액개", source2,
                hashtagList.get(0)+","+hashtagList.get(1)+","+hashtagList.get(6));
        statisticsRepository.save(statistics6);

        //지역별 의약품 수량 및 사용금액
        Statistics statistics7 = new Statistics("","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&autoAuth=true&ctid=fd09b2bc-9220-4c6b-8372-220b7bd51819","https://app.powerbi.com/reportEmbed?reportId=1fa9d1ca-2c6a-4a1e-a7eb-2ea4c6914a1f&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly9XQUJJLUVBU1QtQVNJQS1BLVBSSU1BUlktcmVkaXJlY3QuYW5hbHlzaXMud2luZG93cy5uZXQiLCJlbWJlZEZlYXR1cmVzIjp7Im1vZGVybkVtYmVkIjp0cnVlLCJ1c2FnZU1ldHJpY3NWTmV4dCI6dHJ1ZX19",
                "498b79cc-dfc2-4f7d-b158-f993568b2e02", format.parse("2023-02-14"), format.parse("2023-05-01"),"지역별 의약품 수량 및 사용금액", "MedicalHospital", "지역별 의약품 수량 및 사용금액",  "지역명, 합계 수량개, 합계 사용금액개", source2,
                hashtagList.get(0)+","+hashtagList.get(7));
        statisticsRepository.save(statistics7);

        //병상규모별 의약품 수량 및 사용금액
        Statistics statistics8 = new Statistics("","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&autoAuth=true&ctid=fd09b2bc-9220-4c6b-8372-220b7bd51819","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly9XQUJJLUVBU1QtQVNJQS1BLVBSSU1BUlktcmVkaXJlY3QuYW5hbHlzaXMud2luZG93cy5uZXQiLCJlbWJlZEZlYXR1cmVzIjp7Im1vZGVybkVtYmVkIjp0cnVlLCJ1c2FnZU1ldHJpY3NWTmV4dCI6dHJ1ZX19",
                "498b79cc-dfc2-4f7d-b158-f993568b2e02", format.parse("2023-02-14"), format.parse("2023-05-01"), "병상규모별 의약품 수량 및 사용금액", "MdeicalSupplies", "병상규모별 의약품 수량 및 사용금액", "병상규모별, 합계 수량개, 합계 사용금액계", source2,
                hashtagList.get(0));
        statisticsRepository.save(statistics8);

        //지역별 내과중환자실
        Statistics statistics9 = new Statistics("","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&autoAuth=true&ctid=fd09b2bc-9220-4c6b-8372-220b7bd51819","https://app.powerbi.com/reportEmbed?reportId=498b79cc-dfc2-4f7d-b158-f993568b2e02&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly9XQUJJLUVBU1QtQVNJQS1BLVBSSU1BUlktcmVkaXJlY3QuYW5hbHlzaXMud2luZG93cy5uZXQiLCJlbWJlZEZlYXR1cmVzIjp7Im1vZGVybkVtYmVkIjp0cnVlLCJ1c2FnZU1ldHJpY3NWTmV4dCI6dHJ1ZX19",
                "498b79cc-dfc2-4f7d-b158-f993568b2e02", format.parse("2017-03-16"), format.parse("2023-07-04"), "지역별 내과중환자실", "Emergency","지역별 내과중환자실",  "지역, 내과중환자실 개수", source1,
                hashtagList.get(5)+","+hashtagList.get(6)+","+hashtagList.get(9));
        statisticsRepository.save(statistics9);

        return "statistics 업로드 완료";
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
