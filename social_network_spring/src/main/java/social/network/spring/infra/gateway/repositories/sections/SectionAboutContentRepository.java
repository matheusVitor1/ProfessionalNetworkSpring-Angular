package social.network.spring.infra.gateway.repositories.sections;

import org.springframework.data.jpa.repository.JpaRepository;
import social.network.spring.domain.entities.sections.SectionAboutContent;

public interface SectionAboutContentRepository extends JpaRepository<SectionAboutContent, Long> {
    SectionAboutContent findByUserId(Long userId);
}
