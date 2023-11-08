package social.network.spring.controllers;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.network.spring.dtos.LoginDto;
import social.network.spring.dtos.UserDto;
import social.network.spring.entities.User;
import social.network.spring.service.LoginService;

@RestController
@RequestMapping("login")
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/auth")
    public ResponseEntity<UserDto> login(@RequestBody UserDto auth) {

        User matchedUser = loginService.authenticateUser(auth);
                UserDto userDto = UserDto.builder()
                .id(matchedUser.getId())
                .photoUrl(matchedUser.getPhotoUrl())
                .name(matchedUser.getName())
                .age(matchedUser.getAge())
                .birthday(matchedUser.getBirthday())
                .identity(matchedUser.getIdentity())
                .active(matchedUser.isActive())
                .email(matchedUser.getEmail())
                .build();

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
