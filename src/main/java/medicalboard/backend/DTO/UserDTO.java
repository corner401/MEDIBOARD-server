package medicalboard.backend.DTO;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.util.StringUtils;

@Data
@AllArgsConstructor
public class UserDTO {
    @Nullable
    private Integer id;
    @Nullable
    private String email;
    @Nullable
    private String password;
    @Nullable
    private String dashboard;
    @Nullable
    private String job;

    public UserDTO(Integer userId) {
        this.id = userId;
    }

    public void encodingPassword (PasswordEncoder passwordEncoder) {
        if (StringUtils.isEmpty(password)) {
            return;
        }
        password = passwordEncoder.encode(password);
    }

    public void clearPassword() {
        this.password = null;
    }
}