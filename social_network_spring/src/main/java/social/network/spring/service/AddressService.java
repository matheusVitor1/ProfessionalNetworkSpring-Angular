package social.network.spring.service;

import org.springframework.stereotype.Service;
import social.network.spring.repositories.AddressRepository;
import social.network.spring.repositories.UserRepository;

@Service
public class AddressService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public AddressService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }


}
