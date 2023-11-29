package social.network.spring.infra.gateway.repositories.sections;
import org.springframework.data.jpa.repository.JpaRepository;
import social.network.spring.domain.entities.User;
import social.network.spring.domain.entities.sections.SectionPostsContent;

import java.util.List;
import java.util.Optional;

public interface SectionPostsContentRepository extends JpaRepository<SectionPostsContent, Long> {
    List<SectionPostsContent> findByUser(Optional<User> byId);
}
