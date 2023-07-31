package medicalboard.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@NoArgsConstructor //파라미터 없는 생성자
@AllArgsConstructor //모든 플드를 파라미터로 가지는 생성자
@Setter
@Getter
@Data
@Entity
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    private String powerbiUrl;
    private Date create_date;
    private Date update_date;
    private String title;
    private String table_name;
    private String summary;
    private String content;
    private String source;
    private String hashtag;

    public Statistics(Date create_date, Date update_date, String title, String table_name, String summary, String content, String source, String hashtag){
        this.create_date = create_date;
        this.update_date = update_date;
        this.title = title;
        this.table_name = table_name;
        this.summary = summary;
        this.content = content;
        this.source = source;
        this.hashtag = hashtag;
    }
}
