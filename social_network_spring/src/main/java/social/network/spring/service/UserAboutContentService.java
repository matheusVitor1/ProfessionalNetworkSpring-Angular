package social.network.spring.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import social.network.spring.dtos.UserAboutContentDto;
import social.network.spring.entities.User;
import social.network.spring.entities.UserAboutContent;
import social.network.spring.repositories.UserAboutContentRepository;
import social.network.spring.repositories.UserRepository;

import java.util.Optional;


@Service
public class UserAboutContentService {

    private final UserRepository userRepository;
    private final UserService userService;

    private final UserAboutContentRepository userAboutContentRepository;

    public UserAboutContentService(UserRepository userRepository, UserService userService, UserAboutContentRepository userAboutContentRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.userAboutContentRepository = userAboutContentRepository;
    }

    public UserAboutContentDto createAboutDto (UserAboutContent userAboutContent){
        return UserAboutContentDto.builder()
                .userEmail(userAboutContent.getUser().getEmail())
                .jobPosition(userAboutContent.getJobPosition())
                .userIntroduction(userAboutContent.getUserIntroduction())
                .userBirthday(userAboutContent.getUser().getBirthday())
                .userAge(userAboutContent.getUser().getAge())
                .userPhone(userAboutContent.getUser().getPhone())
                .cep(userAboutContent.getUser().getUserAddress().getCep())
                .city(userAboutContent.getUser().getUserAddress().getLocalidade())
                .uf(userAboutContent.getUser().getUserAddress().getUf())
                .userPhoto(userAboutContent.getUser().getPhotoUrl())
                .build();
    }

    public UserAboutContent findByUserId(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        return userAboutContentRepository.findByUser(optionalUser);
    }

    public boolean saveUserAbout(UserAboutContentDto userAboutContentDto){
        User userFound = userService.findById(userAboutContentDto.getUserId());
        UserAboutContent userAboutFound = findByUserId(userAboutContentDto.getUserId());

        if(userAboutFound == null){
            if (userFound != null){
                UserAboutContent newUserAbout = new UserAboutContent(userAboutContentDto.getJobPosition(), userAboutContentDto.getUserIntroduction(), userFound);
                userAboutContentRepository.save(newUserAbout);
                return true;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuário não encontrado");
            }
        } else {
            int age = userService.calculateAge(userAboutContentDto.getUserBirthday());
            userAboutFound.setJobPosition(userAboutContentDto.getJobPosition());
            userAboutFound.setUserIntroduction(userAboutContentDto.getUserIntroduction());
            userAboutFound.getUser().setPhone(userAboutContentDto.getUserPhone());
            userAboutFound.getUser().setBirthday(userAboutContentDto.getUserBirthday());
            userAboutFound.getUser().setAge(age);
            userAboutFound.getUser().getUserAddress().setCep(userAboutContentDto.getCep());
            userAboutFound.getUser().getUserAddress().setLogradouro(userAboutContentDto.getLogradouro());
            userAboutFound.getUser().getUserAddress().setBairro(userAboutContentDto.getBairro());
            userAboutFound.getUser().getUserAddress().setLocalidade(userAboutContentDto.getCity());
            userAboutFound.getUser().getUserAddress().setUf(userAboutContentDto.getUf());
            userAboutContentRepository.save(userAboutFound);
            return true;
        }
    }

}
