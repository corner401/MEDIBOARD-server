package medicalboard.backend.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Table(name = "`rank`")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rank extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String job;
    @Column(name = "stat_id_list")
    private String statIdList;

    public Rank(String job, String statIdList){
        this.job = job;
        this.statIdList = statIdList;
    }
}
