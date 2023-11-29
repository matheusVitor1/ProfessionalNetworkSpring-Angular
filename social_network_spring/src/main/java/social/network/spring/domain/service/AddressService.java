package social.network.spring.domain.service;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import social.network.spring.domain.dtos.AddressDto;
import social.network.spring.domain.entities.Address;
import social.network.spring.domain.entities.User;
import social.network.spring.infra.gateway.feign.AddressFeign;
import social.network.spring.infra.gateway.repositories.AddressRepository;
import social.network.spring.infra.gateway.repositories.UserRepository;

@RequiredArgsConstructor
@EnableFeignClients
@Service
public class AddressService {
    private final UserService userService;
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
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID do Usuário não pode ser nulo");
        }
        return  addressRepository.findByUserId(userId);
    }

    public boolean saveAddress(AddressDto addressDto){
        if (addressDto.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID do Usuário não pode ser nulo");
        }

        User userFound = userService.findById(addressDto.getUserId());
        if(userFound == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado ");
        }

        Address addressFound = findByUserId(addressDto.getUserId());
        if(addressFound == null){
            Address address = new Address(
                    addressDto.getCep(),
                    addressDto.getLogradouro(),
                    addressDto.getBairro(),
                    addressDto.getLocalidade(),
                    addressDto.getUf(),
                    userFound
            );

            addressRepository.save(address);
            return  true;
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
