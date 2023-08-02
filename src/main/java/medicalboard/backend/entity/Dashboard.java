package medicalboard.backend.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @Column
    private Integer user_id;
    @Column
    private Integer dash_page;


    /*
    @Column
    private Integer stat_id1;
    @Column
    private Integer stat_id2;
    @Column
    private Integer stat_id3;
    @Column
    private Integer stat_id4;
    @Column
    private Integer stat_id5;
    @Column
    private Integer stat_id6;
    @Column
    private Integer stat_id7;
    @Column
    private Integer stat_id8;
    @Column
    private Integer stat_id9;
     */
}
