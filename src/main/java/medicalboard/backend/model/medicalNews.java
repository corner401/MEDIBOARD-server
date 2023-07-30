package medicalboard.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.id.IncrementGenerator;

import java.util.List;

@NoArgsConstructor //파라미터 없는 생성자
@AllArgsConstructor //모든 플드를 파라미터로 가지는 생성자
@Setter
@Data
@Entity
public class medicalNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String url;
    private String createdDate;
    private String contentList;
    private String hashtag;

    //private String writer;

}

