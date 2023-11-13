package social.network.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import social.network.spring.entities.User;
import social.network.spring.entities.EmploymentRecord;

import java.util.List;
import java.util.Optional;

public interface EmploymentRecordRepository extends JpaRepository<EmploymentRecord, Long> {
    List<EmploymentRecord> findByUser(Optional<User> byId);
}
