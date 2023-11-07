package social.network.spring.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import social.network.spring.entities.User;
import social.network.spring.entities.UserPosts;

import java.util.List;
import java.util.Optional;

public interface UserPostsRepository extends JpaRepository<UserPosts, Long> {
    List<UserPosts> findByUser(Optional<User> byId);
}
