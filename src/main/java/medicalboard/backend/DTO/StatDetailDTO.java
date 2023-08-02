package medicalboard.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import medicalboard.backend.model.Article;
import medicalboard.backend.model.Statistics;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
public class StatDetailDTO {

    private Optional<Statistics> statistics;

    private List<Article> articleList;
}
