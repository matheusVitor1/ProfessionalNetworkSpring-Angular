package social.network.spring.infra.gateway.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import social.network.spring.domain.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByUserId(Long userId);

}
