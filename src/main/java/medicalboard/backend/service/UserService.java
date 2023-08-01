package medicalboard.backend.service;

import jakarta.transaction.Transactional;
import medicalboard.backend.DTO.UserDTO;
import medicalboard.backend.mapper.UserMapper;
import medicalboard.backend.entity.User;
import medicalboard.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> getUser(Integer id) {
        return userRepository.findById(id);
    }

    public void join(UserDTO user) {
        user.encodingPassword(passwordEncoder);
        userRepository.save(UserMapper.dtoToEntity(user));
    }

    @Transactional
    public boolean existsByUserEmail(String email){

        return userRepository.existsByEmail(email);
    }


    /**
     * 로그인
     * @param email - 로그인 ID
     * @param password - 비밀번호
     * @return 해당 회원의 DB id
     */
    public Integer login(final String email, final String password) {

        // 회원정보 및 비밀번호 조회
        User user = userRepository.findByEmail(email);
        String encodedPassword = (user == null) ? "" : user.getPassword();

        // 회원 정보 및 비밀번호 체크
        if (user == null || passwordEncoder.matches(password, encodedPassword) == false) {
            return null;
        }

        // 회원 id 리턴
        return user.getId();
    }

}