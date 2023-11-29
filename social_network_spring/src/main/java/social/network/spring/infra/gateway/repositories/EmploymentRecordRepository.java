package social.network.spring.infra.gateway.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import social.network.spring.domain.entities.EmploymentRecord;

import java.util.List;

public interface EmploymentRecordRepository extends JpaRepository<EmploymentRecord, Long> {
    List<EmploymentRecord> findByUserId(Long userId);
}
