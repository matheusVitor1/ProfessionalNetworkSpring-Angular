package social.network.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import social.network.spring.entities.User;
import social.network.spring.entities.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUser(Optional<User> byId);

}
