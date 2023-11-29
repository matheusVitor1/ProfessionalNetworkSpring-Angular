package social.network.spring.infra.controllers.sections;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.network.spring.domain.dtos.sections.SectionHeroContentDto;
import social.network.spring.domain.entities.sections.SectionHeroContent;
import social.network.spring.domain.service.sections.SectionHeroContentService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("hero")
public class SectionHeroContentController {

    private final SectionHeroContentService sectionHeroContentService;
    @PostMapping("/new")
    public ResponseEntity<Map<String, String>> saveUserHero (@RequestBody SectionHeroContentDto sectionHeroContentDto){
        boolean newUser = sectionHeroContentService.saveUserHero(sectionHeroContentDto);
        Map<String, String> response = new HashMap<>();
        if(newUser){
            response.put("message", "Usuário Atualizado");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Falha ao atualizar");
            return new ResponseEntity<>(response,HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity <SectionHeroContentDto> getUserHero(@PathVariable("id") Long id){
        SectionHeroContent userHero = sectionHeroContentService.findByUserId(id);
        SectionHeroContentDto userHeroDto = sectionHeroContentService.createHeroDto(userHero);
        return new ResponseEntity<>(userHeroDto, HttpStatus.OK);
    }

}
