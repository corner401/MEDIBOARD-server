package medicalboard.backend.DTO;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

