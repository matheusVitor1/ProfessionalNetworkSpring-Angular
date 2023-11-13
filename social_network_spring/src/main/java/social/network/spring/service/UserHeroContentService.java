package social.network.spring.service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import social.network.spring.dtos.UserHeroContentDto;
import social.network.spring.entities.User;
import social.network.spring.entities.UserHeroContent;
import social.network.spring.repositories.UserHeroContentRepository;
import social.network.spring.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserHeroContentService {
    private final  UserHeroContentRepository userHeroContentRepository;
    private final UserService userService;

    private final UserRepository userRepository;

    public UserHeroContentService(UserHeroContentRepository userHeroContentRepository, UserService userService, UserRepository userRepository) {
        this.userHeroContentRepository = userHeroContentRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public UserHeroContentDto createHeroDto ( UserHeroContent userHeroContent){
        User userFound = userService.findById(userHeroContent.getUser().getId());
        return UserHeroContentDto.builder()
                .userSelfDescription(userHeroContent.getUserSelfDescription())
                .userId(userHeroContent.getUser().getId())
                .userWallpaper(userHeroContent.getUserWallpaper())
                .textColor(userHeroContent.getTextColor())
                .userNickName(userFound.getName())
                .userPhoto(userFound.getPhotoUrl())
                .build();
    }

    public UserHeroContent findByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return  userHeroContentRepository.findByUser(optionalUser);

    }

    public boolean saveUserHero(UserHeroContentDto userHeroContentDto) {
        User userFound = userService.findById(userHeroContentDto.getUserId());
        UserHeroContent heroFound = findByUserId(userHeroContentDto.getUserId());

        if (heroFound == null) {
            if (userFound != null) {
                UserHeroContent userHeroContent = new UserHeroContent(
                        userHeroContentDto.getUserWallpaper(),
                        userHeroContentDto.getUserSelfDescription(),
                        userHeroContentDto.getTextColor(),
                        userFound
                );
                userHeroContentRepository.save(userHeroContent);
                return true;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado ");
            }
        } else {
            heroFound.setUserWallpaper(userHeroContentDto.getUserWallpaper());
            heroFound.setUserSelfDescription(userHeroContentDto.getUserSelfDescription());
            heroFound.setTextColor(userHeroContentDto.getTextColor());
            userFound.setPhotoUrl(userHeroContentDto.getUserPhoto());
            userFound.setName(userHeroContentDto.getUserNickName());
            userHeroContentRepository.save(heroFound);
            return true;
        }

    }

}
