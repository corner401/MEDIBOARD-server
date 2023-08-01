package medicalboard.backend.rest;

import medicalboard.backend.DTO.UserDTO;
import medicalboard.backend.Mapper.UserMapper;
import medicalboard.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/users/info")
    public ResponseEntity<Object> getUserInfo(@RequestBody UserDTO userDTO) {

        UserDTO result = UserMapper.entityToDto(userService.getUser(userDTO.getId()).get());
        result.clearPassword();
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }
}
