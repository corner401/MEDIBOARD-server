package medicalboard.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor //파라미터 없는 생성자
@AllArgsConstructor //모든 플드를 파라미터로 가지는 생성자
@Table(name = "statistics")
@Setter
@Getter
@Entity
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    private String embededURL;
//    private String Token;
//    private String reportId;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "update_date")
    private Date updateDate;
    @Column
    private String title;
    @Column(name = "table_name")
    private String tableName;
    @Column
    private String summary;
    @Column
    private String content;
    @Column
    private String source;
    @Column
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
