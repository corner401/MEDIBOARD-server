package medicalboard.backend.repository;

import medicalboard.backend.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface StarRepository extends JpaRepository<Star, Integer> {

    List<Star> findAllByUserId(Integer userId);

}
