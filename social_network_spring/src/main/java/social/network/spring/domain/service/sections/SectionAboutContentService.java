package social.network.spring.domain.service.sections;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import social.network.spring.domain.dtos.sections.SectionAboutContentDto;
import social.network.spring.domain.entities.User;
import social.network.spring.domain.entities.sections.SectionAboutContent;
import social.network.spring.domain.service.UserService;
import social.network.spring.infra.gateway.repositories.sections.SectionAboutContentRepository;
import social.network.spring.infra.gateway.repositories.UserRepository;


@Service
public class SectionAboutContentService {

    private final UserService userService;

    private final SectionAboutContentRepository sectionAboutContentRepository;

    public SectionAboutContentService(UserService userService, SectionAboutContentRepository sectionAboutContentRepository) {
        this.userService = userService;
        this.sectionAboutContentRepository = sectionAboutContentRepository;
    }

    public SectionAboutContentDto createAboutDto (SectionAboutContent sectionAboutContent){
        return SectionAboutContentDto.builder()
                .userEmail(sectionAboutContent.getUser().getEmail())
                .jobPosition(sectionAboutContent.getJobPosition())
                .userIntroduction(sectionAboutContent.getUserIntroduction())
                .userBirthday(sectionAboutContent.getUser().getBirthday())
                .userAge(sectionAboutContent.getUser().getAge())
                .userPhone(sectionAboutContent.getUser().getPhone())
                .cep(sectionAboutContent.getUser().getUserAddress().getCep())
                .city(sectionAboutContent.getUser().getUserAddress().getLocalidade())
                .uf(sectionAboutContent.getUser().getUserAddress().getUf())
                .userPhoto(sectionAboutContent.getUser().getPhotoUrl())
                .build();
    }

    public SectionAboutContent findByUserId(Long userId){
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID do Usuário não pode ser nulo");
        }
        return sectionAboutContentRepository.findByUserId(userId);
    }

    public boolean saveUserAbout(SectionAboutContentDto sectionAboutContentDto){

        if (sectionAboutContentDto.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID do Usuário não pode ser nulo");
        }

        User userFound = userService.findById(sectionAboutContentDto.getUserId());
        if(userFound == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuário não encontrado");
        }

        SectionAboutContent userAboutFound = findByUserId(sectionAboutContentDto.getUserId());
        if(userAboutFound == null){
            SectionAboutContent newUserAbout = new SectionAboutContent(
                    sectionAboutContentDto.getJobPosition(),
                    sectionAboutContentDto.getUserIntroduction(),
                    userFound);
            sectionAboutContentRepository.save(newUserAbout);
            return true;
        } else {
            int age = userService.calculateAge(sectionAboutContentDto.getUserBirthday());
            userAboutFound.setJobPosition(sectionAboutContentDto.getJobPosition());
            userAboutFound.setUserIntroduction(sectionAboutContentDto.getUserIntroduction());
            userAboutFound.getUser().setPhone(sectionAboutContentDto.getUserPhone());
            userAboutFound.getUser().setBirthday(sectionAboutContentDto.getUserBirthday());
            userAboutFound.getUser().setAge(age);
            userAboutFound.getUser().getUserAddress().setCep(sectionAboutContentDto.getCep());
            userAboutFound.getUser().getUserAddress().setLogradouro(sectionAboutContentDto.getLogradouro());
            userAboutFound.getUser().getUserAddress().setBairro(sectionAboutContentDto.getBairro());
            userAboutFound.getUser().getUserAddress().setLocalidade(sectionAboutContentDto.getCity());
            userAboutFound.getUser().getUserAddress().setUf(sectionAboutContentDto.getUf());
            sectionAboutContentRepository.save(userAboutFound);
            return true;
        }
    }

}
