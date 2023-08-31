package medicalboard.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dashboard")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dashboard extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn
    private User user;
    @Column(name = "dash_page")
    private Integer dashPage;

    @Convert(converter = StringListConverter.class)
    @Column(name = "stat_list", columnDefinition = "longtext")
    private List<String> statList  = new ArrayList<>();


    public Dashboard(User user, int dash_page) {
        super();
        this.user = user;
        this.dashPage = dash_page;
    }

    public void addStat(Statistics stat) {
        statList.add(stat.getId().toString());
    }

    public void removeStat(Statistics stat) {
        statList.remove(stat.getId().toString());
    }

}
