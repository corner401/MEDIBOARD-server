package medicalboard.backend.service;

import medicalboard.backend.DTO.DashboardDTO;
import medicalboard.backend.DTO.StarDTO;
import medicalboard.backend.DTO.UserDTO;
import medicalboard.backend.entity.Dashboard;
import medicalboard.backend.entity.User;
import medicalboard.backend.model.Statistics;
import medicalboard.backend.repository.DashboardRepository;
import medicalboard.backend.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    private StatisticsRepository statisticsRepository;
    @Autowired
    private UserService userService;

    public void addStar(StarDTO starDTO) {

        User user = userService.getUser(starDTO.getUserId()).get();  // 즐겨찾기 주인 사용자
        Statistics stat = statisticsRepository.getById(starDTO.getStatId());  // 즐겨찾기한 통계자료

        // 대시보드 1개, 즐겨찾기 9개 미만이라고 상정한 코드
        Dashboard dashboard = dashboardRepository.findByUserId(user.getId());

        // 기존에 User가 만든 Dashboard가 존재하면 해당 대시보드에 추가
        if (dashboard != null) {
            dashboard.addStat(stat);  // (트랜잭션 종료 시점에) JPA가 엔터티의 변경을 감지하여 자동으로 업데이트 쿼리를 보낸다.
            dashboardRepository.save(dashboard);  // 근데 변경감지 반영 안 돼서 save 추가함...
        } else {
            // 기존에 User가 만든 Dashboard가 존재하지 않으면
            // Dashboard를 새로 만들기
            Dashboard newDashboard = new Dashboard(user.getId(), 1);
            newDashboard.addStat(stat);
            dashboardRepository.save(newDashboard);
        }
    }

    public DashboardDTO getDashboard(UserDTO userDTO, Integer dashPage) {
        Dashboard dashboard = dashboardRepository.findByUserIdAndDashPage(userDTO.getUserId(), dashPage);
        if (dashboard==null) return null;

        List<String> statIdList = dashboard.getStatList();
        List<Statistics> statList = new ArrayList<Statistics>();
        for (String statId : statIdList) {
            statList.add(statisticsRepository.getById(Integer.parseInt(statId)));
        }

        DashboardDTO dashboardDTO = new DashboardDTO(userDTO.getUserId(), dashPage, statList);
        return dashboardDTO;
    }

}
