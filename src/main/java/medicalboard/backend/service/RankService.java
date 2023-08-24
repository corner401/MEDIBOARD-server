package medicalboard.backend.service;

import medicalboard.backend.entity.Star;
import medicalboard.backend.entity.Statistics;
import medicalboard.backend.repository.StarRepository;
import medicalboard.backend.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RankService {

    @Autowired
    StarRepository starRepository;
    @Autowired
    StatisticsRepository statisticsRepository;

    /*
      rank 추출
       */
    public List<Statistics> getTop3FavoriteStatsByJob(String job){
        List<Star> stars = starRepository.findByJob(job);
        Map<Statistics, Integer> statsCountMap = new HashMap<>();

        for(Star star : stars){
            Integer statId = star.getStatId().getId();
            Statistics stat = statisticsRepository.getById(statId);
            statsCountMap.put(stat, statsCountMap.getOrDefault(stat, 0)+1);
        }

        return statsCountMap.entrySet().stream() //statsCountMap의 엔트리들을 스트림으로 변환하여 연속적인 작업을 수행할
                .sorted(Map.Entry.<Statistics, Integer>comparingByValue().reversed()) //value 값(출현 횟수)을 기준으로 역순으로 정렬
                .limit(3)
                .map(Map.Entry::getKey) //Statistics 객체만을 리스트로 매핑
                .collect(Collectors.toList()); //리스트로 반환
    }
}
