package medicalboard.backend.service;

import medicalboard.backend.DTO.StarDTO;
import medicalboard.backend.entity.User;
import medicalboard.backend.mapper.StarMapper;
import medicalboard.backend.model.Statistics;
import medicalboard.backend.repository.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StarService {

    @Autowired
    private UserService userService;
    @Autowired
    private StarRepository starRepository;

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
