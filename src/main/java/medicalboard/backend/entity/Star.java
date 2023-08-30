package medicalboard.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "star")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Star extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //userid 외래키 추가
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    //    statid 외래키 추가
    @OneToOne
    @JoinColumn(name = "stat_id")
    private Statistics statId;

    @Column
    private String job;

}
