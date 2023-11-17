package social.network.spring.service;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import social.network.spring.dtos.AddressDto;
import social.network.spring.entities.Address;
import social.network.spring.entities.User;
import social.network.spring.feign.AddressFeign;
import social.network.spring.repositories.AddressRepository;
import social.network.spring.repositories.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@EnableFeignClients
@Service
public class AddressService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AddressFeign addressFeign;


    public AddressDto requestCep(AddressDto cepAddress){
        return addressFeign.searchAddressCep(cepAddress.getCep());
    }

    public AddressDto createAddressDto (Address address){
        return AddressDto.builder()
                .uf(address.getUf())
                .cep(address.getCep())
                .bairro(address.getBairro())
                .logradouro(address.getLogradouro())
                .localidade(address.getLocalidade())
                .build();
    }

    public Address findByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return  addressRepository.findByUser(optionalUser);
    }

    public boolean saveAddress(AddressDto addressDto){
        User userFound = userService.findById(addressDto.getUserId());
        Address addressFound = findByUserId(addressDto.getUserId());

        if(addressFound == null){
            if(userFound != null){
                Address address = new Address(
                        addressDto.getCep(),
                        addressDto.getLogradouro(),
                        addressDto.getBairro(),
                        addressDto.getLocalidade(), // Corrigido aqui
                        addressDto.getUf(),
                        userFound
                );

                addressRepository.save(address);
                return  true;

            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado ");
            }
        } else {

            addressFound.setUf(addressDto.getUf());
            addressFound.setCep(addressDto.getCep());
            addressFound.setBairro(addressDto.getBairro());
            addressFound.setLogradouro(addressDto.getLogradouro());
            addressFound.setLocalidade(addressDto.getLocalidade());
            addressRepository.save(addressFound);
            return  true;
        }
    }


}
