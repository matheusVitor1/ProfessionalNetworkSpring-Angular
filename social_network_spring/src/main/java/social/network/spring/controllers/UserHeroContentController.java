package social.network.spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.network.spring.dtos.UserHeroContentDto;
import social.network.spring.entities.UserHeroContent;
import social.network.spring.service.UserHeroContentService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("hero")
public class UserHeroContentController {

    private final UserHeroContentService userHeroContentService;
    @PostMapping("/new")
    public ResponseEntity<Map<String, String>> saveUserHero (@RequestBody UserHeroContentDto userHeroContentDto){
        boolean newUser = userHeroContentService.saveUserHero(userHeroContentDto);
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
    public ResponseEntity <UserHeroContentDto> getUserHero(@PathVariable("id") Long id){
        UserHeroContent userHero = userHeroContentService.findByUserId(id);
        UserHeroContentDto userHeroDto = userHeroContentService.createHeroDto(userHero);
        return new ResponseEntity<>(userHeroDto, HttpStatus.OK);
    }

}
