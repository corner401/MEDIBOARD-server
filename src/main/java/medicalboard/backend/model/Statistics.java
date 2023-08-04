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
//    private String embededURL;
//    private String Token;
//    private String reportId;
    private Date createDate;
    private Date updateDate;
    private String title;
    private String tableName;
    private String summary;
    private String content;
    private String source;
    private String hashtag;


    public Statistics(Date createDate, Date updateDate, String title, String tableName, String summary, String content, String source, String hashtag){
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.title = title;
        this.tableName = tableName;
        this.summary = summary;
        this.content = content;
        this.source = source;
        this.hashtag = hashtag;
    }
}
