package medicalboard.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.Id;

@NoArgsConstructor //파라미터 없는 생성자
@AllArgsConstructor //모든 플드를 파라미터로 가지는 생성자
@Getter
@Entity
@Data
public class Emergency {

    @Id
    private String hpid; //기관코드
    //입력일시
    private String hvidate;
    @Column(name = "duty_name")
    private String dutyName; //병원명
    //일반-int
    private Integer hvec;
    //수술실-int
    private Integer hvoc;
    //신경과-int
    private Integer hvcc;
    //신생아-int
    private Integer hvncc;
    //흉부외과
    private Integer hvccc;
    //중환자실-일반
    private Integer hvicc;
    //입원실-일반
    private Integer hvgc;
    //CT가용(가/부)
    private String hvctayn;
    //MRI가용
    private String hvmriayn;
    //혈관촬영기가용
    private String hvangioayn;
    //인공호흡기가용
    private String hvventiayn;
    //인큐베이터가용
    private String hvincuayn;
    //구급차가용
    private String hvamyn;
    private Integer hv2; //내과중환자실
    private Integer hv3; //외과중환자실
    //외과입원실(정형외과)
    private Integer hv4;
    private String hv5; //신경과입원실 - 유무
    //신경와과
    private Integer hv6;
    //화상
    private Integer hv8;
    //외상
    private Integer hv9;
    private String hv10; //소아(VENTI) -유무
    //인큐베이터-유무
    private String hv11;
    //격리진료구역(음압격리병상)
    private Integer hv13;
    //일반격리병상
    private Integer hv14;
    //소아음압격리
    private Integer hv15;
    //소아일반격리
    private Integer hv16;
    //응급전용, 중환자실, 음압격리
    private Integer hv17;
    //응급전용, 중환자실, 일반격리
    private Integer hv18;
    //응급전용, 입원실, 음압격리
    private Integer hv19;
    //응급전용, 입원실, 일반격리
    private Integer hv21;
    //감염병 전담병상 중환자실
    private Integer hv22;
    //감염병 전담병상 중환자실 내 음압격리병상
    private Integer hv23;
    //[감염] 중증 병상
    private Integer hv24;
    //[감염] 준-중증 병상
    private Integer hv25;
    //[감염] 중등증 병상
    private Integer hv26;
    //응급실 음압 격리 병상
    private Integer hv29;
    //응급실 일반 격리 병상
    private Integer hv30;
    //[응급전용] 중환자실
    private Integer hv31;
    //[중환자실] 소아
    private Integer hv32;
    //[응급전용] 소아중환자실
    private Integer hv33;
    //[응급전용] 입원실
    private Integer hv36;
    //[응급전용] 소아입원실
    private Integer hv37;
    //입원실] 외상전용
    private Integer hv38;
    //[기타] 외상전용 수술실
    private Integer hv40;
    //응급실전화
    private String dutytel3;
}