package social.network.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import social.network.spring.entities.User;
import social.network.spring.entities.UserAboutContent;
import social.network.spring.entities.UserHeroContent;

import java.util.Optional;

public interface UserAboutContentRepository  extends JpaRepository<UserAboutContent, Long> {
    UserAboutContent findByUser(Optional<User> byId);
}
