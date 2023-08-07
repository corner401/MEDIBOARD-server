package medicalboard.backend.repository;

import medicalboard.backend.datamodel.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyRepository extends JpaRepository<Emergency, Integer>{


}