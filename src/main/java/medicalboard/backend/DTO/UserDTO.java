package medicalboard.backend.DTO;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.util.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Nullable
    private Integer userId;
    @Nullable
    private String email;
    @Nullable
    private String password;
    @Nullable
    private String dashboard;
    @Nullable
    private String job;

    public UserDTO(Integer userId) {
        this.userId = userId;
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