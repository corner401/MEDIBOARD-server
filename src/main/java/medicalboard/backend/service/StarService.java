package medicalboard.backend.service;

import medicalboard.backend.DTO.StarDTO;
import medicalboard.backend.entity.Star;
import medicalboard.backend.entity.User;
import medicalboard.backend.mapper.StarMapper;
import medicalboard.backend.entity.Statistics;
import medicalboard.backend.repository.StarRepository;
import medicalboard.backend.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class StarService {

    @Autowired
    private UserService userService;
    @Autowired
    private StarRepository starRepository;
    @Autowired
    StatisticsService statisticsService;
    @Autowired
    StatisticsRepository statisticsRepository;

    public void addStar(StarDTO star) {
        User user = userService.getUser(star.getUserId()).get();
        star.setJob(user.getJob());
        // Star 테이블에 추가
        starRepository.save(StarMapper.dtoToEntity(star));
    }

    public List<Statistics> getTop3(String job) {

        List<Statistics> result = new ArrayList<>();
        return result;
    }



}
