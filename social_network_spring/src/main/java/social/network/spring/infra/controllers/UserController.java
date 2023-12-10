package social.network.spring.infra.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import social.network.spring.domain.dtos.UserAuthDto;
import social.network.spring.domain.dtos.UserDto;
import social.network.spring.domain.entities.User;
import social.network.spring.domain.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<UserAuthDto> createUser (@RequestBody UserDto userDto){
        try{
            return ResponseEntity.ok(userService.saveUser(userDto));
        } catch (ResponseStatusException e){
            return new  ResponseEntity<>(null, e.getStatusCode());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAuthDto> getUserInformation(@PathVariable("id") Long id) {
        User user = userService.findById(id);

        if (user != null) {
            UserAuthDto userDto = userService.createAuthenticatedUserDto(user);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
