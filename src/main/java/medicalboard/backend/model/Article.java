package medicalboard.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@NoArgsConstructor //파라미터 없는 생성자
@AllArgsConstructor //모든 플드를 파라미터로 가지는 생성자
@Setter
@Getter
@Data
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String writer;
    private String content;
    private Date create_date;
    private Date update_date;
    private String link;
    private String hashtag;
    private String newspaper;


}

