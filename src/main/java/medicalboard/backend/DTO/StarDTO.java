package medicalboard.backend.DTO;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import medicalboard.backend.entity.Statistics;
import medicalboard.backend.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.util.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StarDTO {
    @Nullable
    private Integer id;
    @Nullable
    private User userId;
    @Nullable
    private Statistics statId;
    @Nullable
    private String job;


}

