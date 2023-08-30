package medicalboard.backend.mapper;

import medicalboard.backend.DTO.StarDTO;
import medicalboard.backend.entity.Star;
import medicalboard.backend.entity.Statistics;
import medicalboard.backend.entity.User;
import medicalboard.backend.repository.StatisticsRepository;
import medicalboard.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StarMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatisticsRepository statisticsRepository;

    // Method to convert Star DTO to Star Entity
    public Star dtoToEntity(StarDTO starDTO) {
        if (starDTO == null) {
            return null;
        }

        Star star = new Star();
        star.setId(starDTO.getId());
        star.setJob(starDTO.getJob());

        // Set the User entity for Star
        Integer userId = starDTO.getUserId();
        if (userId != null) {
            Optional<User> userOpt = userRepository.findById(userId);
            if (userOpt.isPresent()) {
                star.setUserId(userOpt.get());
            }
        }

        // Set the Statistics entity for Star
        Integer statId = starDTO.getStatId();
        if (statId != null) {
            Optional<Statistics> statOpt = statisticsRepository.findById(statId); // This assumes you have a findById method in your StatisticsRepository
            if (statOpt.isPresent()) {
                star.setStatId(statOpt.get());
            }
        }

        return star;
    }

    // Method to convert Star Entity to Star DTO
    public static StarDTO entityToDto(Star star) {
        if (star == null) {
            return null;
        }

        StarDTO starDTO = new StarDTO();
        starDTO.setId(star.getId());
        starDTO.setJob(star.getJob());
        starDTO.setUserId(star.getUserId().getId());// 외래키 id만 가져오기
        starDTO.setStatId(star.getStatId().getId());// 외래키 id만 가져오기

        return starDTO;
    }
}

