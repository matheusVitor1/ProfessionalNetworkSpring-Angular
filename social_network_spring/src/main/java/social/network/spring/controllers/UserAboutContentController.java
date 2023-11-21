package social.network.spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.network.spring.dtos.UserAboutContentDto;
import social.network.spring.entities.UserAboutContent;
import social.network.spring.service.UserAboutContentService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("about")
public class UserAboutContentController {
    private final UserAboutContentService userAboutContentService;
    @PostMapping("/new")
    public ResponseEntity<Map<String, String>> saveAboutContent(@RequestBody UserAboutContentDto userAboutContentDto){
        boolean savedAbout = userAboutContentService.saveUserAbout(userAboutContentDto);
        Map<String,String> response = new HashMap<>();
        if (savedAbout){
            response.put("message", "Conteúdo do About Salvo");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Erro ao salvar o conteúdo do About");
            return  new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity <UserAboutContentDto> getUserAbout(@PathVariable("id") Long userId){
        UserAboutContent userAboutFound = userAboutContentService.findByUserId(userId);
        UserAboutContentDto userAboutDto = userAboutContentService.createAboutDto(userAboutFound);
        return new ResponseEntity<>(userAboutDto, HttpStatus.OK);
    }

}
