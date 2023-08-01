package medicalboard.backend.Mapper;

import medicalboard.backend.DTO.UserDTO;
import medicalboard.backend.entity.User;

import java.util.Optional;

public class UserMapper {

    public static UserDTO entityToDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getDashboard(),
                user.getJob()
        );
    }

    public static User dtoToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setDashboard(userDTO.getDashboard());
        user.setJob(userDTO.getJob());
        return user;
    }
}