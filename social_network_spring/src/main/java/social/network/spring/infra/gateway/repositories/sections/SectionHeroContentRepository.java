package social.network.spring.infra.gateway.repositories.sections;

import org.springframework.data.jpa.repository.JpaRepository;
import social.network.spring.domain.entities.sections.SectionHeroContent;

public interface SectionHeroContentRepository extends JpaRepository<SectionHeroContent, Long> {
   SectionHeroContent findByUserId(Long userId);
}
