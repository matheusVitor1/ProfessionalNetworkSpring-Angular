package social.network.spring.infra.controllers.sections;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import social.network.spring.domain.dtos.sections.SectionHeroContentDto;
import social.network.spring.domain.entities.sections.SectionHeroContent;
import social.network.spring.domain.service.sections.SectionHeroContentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("hero")
public class SectionHeroContentController {

    private final SectionHeroContentService sectionHeroContentService;
    @PostMapping("/new")
    public ResponseEntity<String> saveUserHero (@RequestBody SectionHeroContentDto sectionHeroContentDto){

        try {
            sectionHeroContentService.saveUserHero(sectionHeroContentDto);
            return new ResponseEntity<>( HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity <SectionHeroContentDto> getUserHero(@PathVariable("id") Long id){
        try {
            SectionHeroContent userHero = sectionHeroContentService.findByUserId(id);
            SectionHeroContentDto userHeroDto = sectionHeroContentService.createHeroDto(userHero);
            return new ResponseEntity<>(userHeroDto, HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getStatusCode());
        }

    }

    @GetMapping("/all")
    public ResponseEntity<List<SectionHeroContentDto>> getAllHeroes(){
        try{
            return new ResponseEntity<>(sectionHeroContentService.getAll(), HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

}
