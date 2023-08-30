package medicalboard.backend.service;

import medicalboard.backend.DTO.DashboardDTO;
import medicalboard.backend.DTO.StarDTO;
import medicalboard.backend.DTO.UserDTO;
import medicalboard.backend.entity.Dashboard;
import medicalboard.backend.entity.User;
import medicalboard.backend.entity.Statistics;
import medicalboard.backend.repository.DashboardRepository;
import medicalboard.backend.repository.StarRepository;
import medicalboard.backend.repository.StatisticsRepository;
import medicalboard.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    private StatisticsRepository statisticsRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private StarRepository starRepository;
    @Autowired
    private UserRepository userRepository;


    public void addStar(StarDTO starDTO) {

        Integer userId = starDTO.getUserId();  // 즐겨찾기 주인 사용자 id
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent()) {
            User user = userOpt.get(); //즐겨찾기 주인 사용자 User 객체

            // 대시보드 1개, 즐겨찾기 9개 미만이라고 상정한 코드
            Dashboard dashboard = dashboardRepository.findByUserId(user.getId());

            Integer statId = starDTO.getStatId();  // 즐겨찾기한 통계자료 id
            Optional<Statistics> statOpt = statisticsRepository.findById(statId);
            if (statOpt.isPresent()) {
                Statistics stat = statOpt.get();   // 즐겨찾기한 통계자료 Statistics 객체

                // 기존에 User가 만든 Dashboard가 존재하면 해당 대시보드에 추가
                if (dashboard != null) {
                    dashboard.addStat(stat);  // (트랜잭션 종료 시점에) JPA가 엔터티의 변경을 감지하여 자동으로 업데이트 쿼리를 보낸다.
                    dashboardRepository.save(dashboard);  // 근데 변경감지 반영 안 돼서 save 추가함...
                    user.setDashboard(dashboard);  // User 엔터티에 setDashboardId 메서드가 정의되어 있다고 가정
                    userRepository.save(user);

                } else {
                    // 기존에 User가 만든 Dashboard가 존재하지 않으면
                    // Dashboard를 새로 만들기
                    Dashboard newDashboard = new Dashboard(user, 1);
                    newDashboard.addStat(stat);
                    dashboardRepository.save(newDashboard);
                    user.setDashboard(dashboard);  // User 엔터티에 setDashboardId 메서드가 정의되어 있다고 가정
                    userRepository.save(user);

                }
            }

        }
    }

    /*
    statistic -> dashboard 저장
     */
    public void addStatToDashboard(Integer dashboardId, Statistics stat){
        Dashboard dashboard = dashboardRepository.findById(dashboardId).orElse(null);
        if(dashboard != null){
            dashboard.addStat(stat);
            dashboardRepository.save(dashboard);
        }
    }



    public DashboardDTO getDashboard(UserDTO userDTO, Integer dashPage) {
        Integer userId = userDTO.getUserId();
        User user = userRepository.getById(userId);
        Dashboard dashboard = dashboardRepository.findByUserIdAndDashPage(user, dashPage);
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
