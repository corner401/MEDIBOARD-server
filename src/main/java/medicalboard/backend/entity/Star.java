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
    @Column
    private Integer user_id;
    @Column
    private Integer stat_id;
    @Column
    private String job;

}
