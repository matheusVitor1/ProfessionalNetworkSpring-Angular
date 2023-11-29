package social.network.spring.infra.controllers.sections;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import social.network.spring.domain.dtos.sections.SectionAboutContentDto;
import social.network.spring.domain.entities.sections.SectionAboutContent;
import social.network.spring.domain.service.sections.SectionAboutContentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("about")
public class SectionAboutContentController {
    private final SectionAboutContentService sectionAboutContentService;
    @PostMapping("/new")
    public ResponseEntity <String> saveAboutContent(@RequestBody SectionAboutContentDto sectionAboutContentDto){
        try{
            sectionAboutContentService.saveUserAbout(sectionAboutContentDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity <SectionAboutContentDto> getUserAbout(@PathVariable("id") Long userId){
        try{
            SectionAboutContent userAboutFound = sectionAboutContentService.findByUserId(userId);
            SectionAboutContentDto userAboutDto = sectionAboutContentService.createAboutDto(userAboutFound);
            return new ResponseEntity<>(userAboutDto, HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getStatusCode());
        }

    }

}
