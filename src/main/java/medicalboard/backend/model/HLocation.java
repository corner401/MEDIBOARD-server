package medicalboard.backend.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.sql.Blob;

@NoArgsConstructor //파라미터 없는 생성자
@AllArgsConstructor //모든 플드를 파라미터로 가지는 생성자
@Entity
@Data
public class HLocation { //응급기관 위치 정보 조회

    @Id
    private String HPID; //기관 번호
    private String DUTYNAME; //기관명
    private String DUTYADDR; //주소
    private String DUTYDIV; //병원분류
    private String DUTYDIVNAM; //병원분류명
    private String DUTYTEL3; //응급실 전화
    private String DUTYERYN; //응급실 운영 여부
    private String DUTYTIME1C; //진료시간(월)
    private String DUTYTIME2C; //진료시간(화)
    private String DUTYTIME3C; //진료시간(수)
    private String DUTYTIME4C; //진료시간(목)
    private String DUTYTIME5C; //진료시간(금)
    private String DUTYTIME6C; //진료시간(토)
    private String DUTYTIME7C; //진료시간(일)
    private String DUTYTIME8C; //진료시간(공휴일)
    private double WGS84LAT; //병원 위도
    private double WGS84LON; //병원 경도

}
