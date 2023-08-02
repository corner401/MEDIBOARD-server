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
    private Integer user_id;
    @Nullable
    private Integer stat_id;
    @Nullable
    private String job;

}