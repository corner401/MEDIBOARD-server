package medicalboard.backend.repository;

import medicalboard.backend.model.HLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HLocationRepository  extends JpaRepository<HLocation, Integer> {
}
