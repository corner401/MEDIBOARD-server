package medicalboard.backend.mapper;

import medicalboard.backend.DTO.StarDTO;
import medicalboard.backend.entity.Star;

public class StarMapper {

    // Method to convert Star DTO to Star Entity
    public static Star dtoToEntity(StarDTO starDTO) {
        if (starDTO == null) {
            return null;
        }

        Star star = new Star(
                starDTO.getId(),
                starDTO.getUserId(),
                starDTO.getStatId(),
                starDTO.getJob()
        );

        return star;
    }

    // Method to convert Star Entity to Star DTO
    public static StarDTO entityToDto(Star star) {
        if (star == null) {
            return null;
        }

        StarDTO starDTO = new StarDTO(
                star.getId(),
                star.getUserId(),
                star.getStatId(),
                star.getJob()
        );

        return starDTO;
    }
}

