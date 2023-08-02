package medicalboard.backend.repository;

import medicalboard.backend.entity.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DashboardRepository extends JpaRepository<Dashboard, Integer> {

    Dashboard findByUserId(Integer userId);
    List<Dashboard> findAllByUserId(Integer userId);

}
