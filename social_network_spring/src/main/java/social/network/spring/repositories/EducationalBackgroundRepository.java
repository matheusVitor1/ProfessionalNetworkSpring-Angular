package social.network.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import social.network.spring.entities.EducationalBackground;
import social.network.spring.entities.User;

import java.util.List;
import java.util.Optional;

public interface EducationalBackgroundRepository extends JpaRepository<EducationalBackground, Long> {

    List<EducationalBackground> findByUser(Optional<User> byId);
}
