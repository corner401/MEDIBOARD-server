package medicalboard.backend.DTO;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.util.StringUtils;

@Data
@AllArgsConstructor
public class StarDTO {
    @Nullable
    private Integer id;
    @Nullable
    private Integer userId;
    @Nullable
    private Integer statId;
    @Nullable
    private String job;

}