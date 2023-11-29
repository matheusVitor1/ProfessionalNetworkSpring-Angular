package social.network.spring.infra.gateway.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import social.network.spring.domain.entities.EducationalBackground;

import java.util.List;

public interface EducationalBackgroundRepository extends JpaRepository<EducationalBackground, Long> {

    List<EducationalBackground> findByUserId(Long userId);
}
