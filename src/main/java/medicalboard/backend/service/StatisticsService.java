package medicalboard.backend.service;

import medicalboard.backend.entity.Statistics;
import medicalboard.backend.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    @Autowired
    StatisticsRepository statisticsRepository;
    public Statistics getStatisticsById(Integer statId) {
        return statisticsRepository.findById(statId).orElse(null);
    }
}
