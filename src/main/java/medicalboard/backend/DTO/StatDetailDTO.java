package medicalboard.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import medicalboard.backend.entity.Article;
import medicalboard.backend.entity.Statistics;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
public class StatDetailDTO {

    private Optional<Statistics> statistics;
    private List<Article> articleList;
}
