package medicalboard.backend.DTO;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private Integer id;
    private String title;
    private String writer;
    private String content;
    private String link;
    private String hashtag;
    private String newspaper;
}