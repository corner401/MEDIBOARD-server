package medicalboard.backend.repository;

import medicalboard.backend.entity.Dashboard;
import medicalboard.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DashboardRepository extends JpaRepository<Dashboard, Integer> {

    Dashboard findByUserId(Integer userId);
    List<Dashboard> findAllByUserId(User userId);

    Dashboard findByUserIdAndDashPage(User userId, Integer dashPage);
}
