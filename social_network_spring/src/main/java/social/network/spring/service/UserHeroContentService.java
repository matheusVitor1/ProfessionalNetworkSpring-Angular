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
        return UserHeroContentDto.builder()
                .userSelfDescription(userHeroContent.getUserSelfDescription())
                .userId(userHeroContent.getUser().getId())
                .userWallpaper(userHeroContent.getUserWallpaper())
                .userNickName(userHeroContent.getUser().getName())
                .userPhoto(userHeroContent.getUser().getPhotoUrl())
                .build();
    }

    public UserHeroContent findByUserId(Long userId) {

        return  userHeroContentRepository.findByUserId(userId);

    }

    public boolean saveUserHero(UserHeroContentDto userHeroContentDto) {
        User userFound = userService.findById(userHeroContentDto.getUserId());
        UserHeroContent heroFound = findByUserId(userHeroContentDto.getUserId());

        if (heroFound == null) {
            if (userFound != null) {
                UserHeroContent userHeroContent = new UserHeroContent(
                        userHeroContentDto.getUserWallpaper(),
                        userHeroContentDto.getUserSelfDescription(),
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
            heroFound.getUser().setPhotoUrl(userHeroContentDto.getUserPhoto());
            heroFound.getUser().setName(userHeroContentDto.getUserNickName());
            userHeroContentRepository.save(heroFound);
            return true;
        }

    }

}
