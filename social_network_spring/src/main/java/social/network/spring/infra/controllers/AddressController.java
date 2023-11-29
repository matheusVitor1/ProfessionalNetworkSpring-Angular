package social.network.spring.infra.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import social.network.spring.domain.dtos.AddressDto;
import social.network.spring.domain.entities.Address;
import social.network.spring.domain.service.AddressService;

@RequiredArgsConstructor
@RestController
@RequestMapping("address")
public class AddressController {

    private final AddressService addressService;

    @RequestMapping("/consulta")
    public ResponseEntity requestCep(@RequestBody AddressDto addressRequest){
        return ResponseEntity.ok(addressService.requestCep(addressRequest));
    }
    @PostMapping("/new")
    public ResponseEntity <String> saveAddres(@RequestBody AddressDto addressDto){
        try{
            addressService.saveAddress(addressDto);
            return  new ResponseEntity<>(HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity <AddressDto> getUserAddress (@PathVariable("id") Long id){
        try{
            Address address = addressService.findByUserId(id);
            AddressDto addressToDto = addressService.createAddressDto(address);
            return new  ResponseEntity<>(addressToDto,HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(null, e.getStatusCode());
        }

    }


}
