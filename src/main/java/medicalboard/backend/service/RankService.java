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
            Integer statId = star.getStatId();
            Statistics stat = statisticsRepository.getById(statId);
            statsCountMap.put(stat, statsCountMap.getOrDefault(stat, 0)+1);
        }

        return statsCountMap.entrySet().stream()
                .sorted(Map.Entry.<Statistics, Integer>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
