package medicalboard.backend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String email;
    @Column
    private String password;

    //외래키 추가 리스트로 나타내야함!!!
    @ManyToOne
    @JoinColumn(nullable = true)
    private Dashboard dashboard;

//    @Column(nullable = true)
//    private String dashboard;
    @Column
    private String job;
}

