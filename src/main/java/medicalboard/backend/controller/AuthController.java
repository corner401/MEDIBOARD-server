package medicalboard.backend.controller;

import medicalboard.backend.DTO.UserDTO;
import medicalboard.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/api/auth/test")
    public ResponseEntity<Object> test(@RequestBody UserDTO user) {
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    /*
     * 회원가입
     * */
    @PostMapping("/api/auth/register")
    public ResponseEntity<Object> register(@RequestBody UserDTO user) {
        if (userService.existsByUserEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.OK).body("이메일 중복");
        }

        userService.join(user);
        return ResponseEntity.status(HttpStatus.OK).body("회원가입 성공");
    }

    /*
     *  로그인
     * */
    @PostMapping("/api/auth/login")
    public ResponseEntity<Object> login(@RequestBody UserDTO userDTO) {
        Integer userId = userService.login(userDTO.getEmail(), userDTO.getPassword());

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.OK).body("이메일 또는 비밀번호가 정확하지 않습니다.");
        }

        // UserDTO에 id만 넣어서 반환
        UserDTO result = new UserDTO(userId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
