package medicalboard.backend.service;

import medicalboard.backend.DTO.StarDTO;
import medicalboard.backend.entity.Star;
import medicalboard.backend.entity.User;
import medicalboard.backend.mapper.StarMapper;
import medicalboard.backend.entity.Statistics;
import medicalboard.backend.repository.StarRepository;
import medicalboard.backend.repository.StatisticsRepository;
import medicalboard.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class StarService {

    @Autowired
    private UserService userService;
    @Autowired
    private StarRepository starRepository;
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private StatisticsRepository statisticsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StarMapper starMapper;

    public void addStar(StarDTO starDTO) {
        Integer userId = starDTO.getUserId();
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent()){
            User user = userOpt.get();
            starDTO.setJob(user.getJob());
            starDTO.setUserId(user.getId());
            starDTO.setStatId(starDTO.getStatId());


        }
        // Star 테이블에 추가
        starRepository.save(starMapper.dtoToEntity(starDTO));
    }

    public List<Statistics> getTop3(String job) {

        List<Statistics> result = new ArrayList<>();
        return result;
    }



}
