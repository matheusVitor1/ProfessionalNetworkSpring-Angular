package social.network.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import social.network.spring.entities.User;
import social.network.spring.entities.UserHeroContent;

import java.util.Optional;

public interface UserHeroContentRepository extends JpaRepository<UserHeroContent, Long> {
   UserHeroContent findByUser(Optional<User> byId);
}
