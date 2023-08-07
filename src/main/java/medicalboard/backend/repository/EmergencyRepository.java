package medicalboard.backend.repository;

import medicalboard.backend.model.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EmergencyRepository extends JpaRepository<Emergency, Integer>{


}