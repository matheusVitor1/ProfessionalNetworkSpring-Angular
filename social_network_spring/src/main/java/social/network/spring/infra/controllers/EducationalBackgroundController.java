package social.network.spring.infra.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import social.network.spring.domain.dtos.EducationalBackgroundDto;
import social.network.spring.domain.dtos.EmploymentRecordDto;
import social.network.spring.domain.service.EducationalBackgroundService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("educationalBackground")
public class EducationalBackgroundController {

    private final EducationalBackgroundService educationalBackgroundService;

    @GetMapping("/{id}")
    public ResponseEntity<List<EducationalBackgroundDto>> getCustomerEduRecord(@PathVariable("id") Long id) {
        try {
            List<EducationalBackgroundDto> records = educationalBackgroundService.getAllByUser(id);
            if (!records.isEmpty()) {
                return new ResponseEntity<>(records, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<String> saveRecord(@RequestBody EducationalBackgroundDto educationalBackgroundDto){
        try{
            educationalBackgroundService.save(educationalBackgroundDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity< String> editRecord(@RequestBody EducationalBackgroundDto educationalBackgroundDto){

        try{
            educationalBackgroundService.edit(educationalBackgroundDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeRecord(@PathVariable("id") Long id){

        try{
            educationalBackgroundService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
