package social.network.spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import social.network.spring.dtos.UserDto;
import social.network.spring.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<Map<String, String>> createUser (@RequestBody UserDto userDto){
        boolean newUser = userService.saveUser(userDto);
        Map<String, String> response = new HashMap<>();
        if(newUser){
            response.put("message", "usuário criado");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "usuário já cadastrado");
            return new ResponseEntity<>(response,HttpStatus.CONFLICT);
        }
    }
}
