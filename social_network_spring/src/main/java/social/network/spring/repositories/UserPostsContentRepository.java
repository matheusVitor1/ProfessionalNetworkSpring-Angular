package social.network.spring.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import social.network.spring.entities.User;
import social.network.spring.entities.UserPostsContent;

import java.util.List;
import java.util.Optional;

public interface UserPostsContentRepository extends JpaRepository<UserPostsContent, Long> {
    List<UserPostsContent> findByUser(Optional<User> byId);
}
