package medicalboard.backend.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import medicalboard.backend.model.Statistics;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {

    private Integer id;
    private Integer userId;
    private Integer dashPage;

    private List<Statistics> statList;
    public DashboardDTO(Integer userId, Integer dashPage, List<Statistics> statList) {
        this.userId = userId;
        this.dashPage = dashPage;
        this.statList = statList;
    }
}
