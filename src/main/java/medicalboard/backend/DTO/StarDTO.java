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