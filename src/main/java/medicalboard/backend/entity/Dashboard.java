package medicalboard.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Table(name = "dashboard")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dashboard extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "dash_page")
    private Integer dashPage;

    @Convert(converter = StringListConverter.class)
    @Column(name = "stat_list", columnDefinition = "longtext")
    private List<String> statList  = new ArrayList<>();


    public Dashboard(Integer userId, int dash_page) {
        super();
        this.userId = userId;
        this.dashPage = dash_page;
    }

    public void addStat(Statistics stat) {
        statList.add(stat.getId().toString());
    }

    public void removeStat(Statistics stat) {
        statList.remove(stat.getId().toString());
    }

}
