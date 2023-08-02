package medicalboard.backend.service;

import medicalboard.backend.DTO.StarDTO;
import medicalboard.backend.entity.Star;
import medicalboard.backend.entity.User;
import medicalboard.backend.mapper.StarMapper;
import medicalboard.backend.repository.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StarService {
/*
    @Autowired
    private UserService userService;
    @Autowired
    private StarRepository starRepository;

    public void addStar(StarDTO star) {
        User user = userService.getUser(star.getUser_id()).get();
        star.setJob(user.getJob());
        // Star 테이블에 저장
        starRepository.save(StarMapper.dtoToEntity(star));
        // Dashboard 테이블에 저장

    }

    public List<Star> findStarsByUserId(Integer userId) {
        return starRepository.findByUser_id(userId);
    }

 */
}
