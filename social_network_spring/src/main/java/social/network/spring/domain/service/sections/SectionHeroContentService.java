package social.network.spring.domain.service.sections;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import social.network.spring.domain.dtos.sections.SectionHeroContentDto;
import social.network.spring.domain.entities.User;
import social.network.spring.domain.entities.sections.SectionHeroContent;
import social.network.spring.domain.service.UserService;
import social.network.spring.infra.gateway.repositories.sections.SectionHeroContentRepository;
import social.network.spring.infra.gateway.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionHeroContentService {
    private final SectionHeroContentRepository sectionHeroContentRepository;
    private final UserService userService;



    public SectionHeroContentService(SectionHeroContentRepository sectionHeroContentRepository, UserService userService) {
        this.sectionHeroContentRepository = sectionHeroContentRepository;
        this.userService = userService;
    }

    public List<SectionHeroContentDto> getAll(){
        return createHeroAsListDto(sectionHeroContentRepository.findAll());
    }

    public List<SectionHeroContentDto> createHeroAsListDto(List<SectionHeroContent> sectionHeroContents) {

        if(sectionHeroContents == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        return sectionHeroContents.stream()
                .map(sectionHeroContent -> SectionHeroContentDto.builder()
                        .id(sectionHeroContent.getId())
                        .userSelfDescription(sectionHeroContent.getUserSelfDescription())
                        .userId(sectionHeroContent.getUser().getId())
                        .userWallpaper(sectionHeroContent.getUserWallpaper())
                        .userNickName(sectionHeroContent.getUser().getName())
                        .userPhoto(sectionHeroContent.getUser().getPhotoUrl())
                        .build())
                .collect(Collectors.toList());
    }


    public SectionHeroContentDto createHeroDto (SectionHeroContent sectionHeroContent){
        return SectionHeroContentDto.builder()
                .id(sectionHeroContent.getId())
                .userSelfDescription(sectionHeroContent.getUserSelfDescription())
                .userId(sectionHeroContent.getUser().getId())
                .userWallpaper(sectionHeroContent.getUserWallpaper())
                .userNickName(sectionHeroContent.getUser().getName())
                .userPhoto(sectionHeroContent.getUser().getPhotoUrl())
                .build();
    }

    public SectionHeroContent findByUserId(Long userId) {

        return  sectionHeroContentRepository.findByUserId(userId);

    }

    public boolean saveUserHero(SectionHeroContentDto sectionHeroContentDto) {
        User userFound = userService.findById(sectionHeroContentDto.getUserId());
        SectionHeroContent heroFound = findByUserId(sectionHeroContentDto.getUserId());

        if (heroFound == null) {
            if (userFound != null) {
                SectionHeroContent sectionHeroContent = new SectionHeroContent(
                        sectionHeroContentDto.getUserWallpaper(),
                        sectionHeroContentDto.getUserSelfDescription(),
                        userFound
                );
                sectionHeroContentRepository.save(sectionHeroContent);
                return true;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado ");
            }
        } else {
            heroFound.setUserWallpaper(sectionHeroContentDto.getUserWallpaper());
            heroFound.setUserSelfDescription(sectionHeroContentDto.getUserSelfDescription());
            heroFound.getUser().setPhotoUrl(sectionHeroContentDto.getUserPhoto());
            heroFound.getUser().setName(sectionHeroContentDto.getUserNickName());
            sectionHeroContentRepository.save(heroFound);
            return true;
        }

    }

}
