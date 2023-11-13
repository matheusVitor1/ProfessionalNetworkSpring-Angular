package social.network.spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.network.spring.dtos.AddressDto;
import social.network.spring.entities.Address;
import social.network.spring.service.AddressService;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity <Map<String, String>> saveAddres(@RequestBody AddressDto addressDto){
        boolean newAddres = addressService.saveAddress(addressDto);
        Map<String, String> response = new HashMap<>();
        if (newAddres) {
            response.put("message", "Endereço Salvo");
            return  new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity <AddressDto> getUserAddress (@PathVariable("id") Long id){
        Address address = addressService.findByUserId(id);
        AddressDto addressToDto = addressService.createAddressDto(address);
        return new  ResponseEntity<>(addressToDto, HttpStatus.OK);
    }


}
