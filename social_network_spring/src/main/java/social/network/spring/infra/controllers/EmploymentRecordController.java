package social.network.spring.infra.controllers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import social.network.spring.domain.dtos.EmploymentRecordDto;
import social.network.spring.domain.service.EmploymentRecordService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("employmentRecord")
public class EmploymentRecordController {

    private final EmploymentRecordService employmentRecordService;


    @GetMapping("/{id}")
    public ResponseEntity<List<EmploymentRecordDto>> getCustomerEmpRecord(@PathVariable("id") Long id) {
        try {
            List<EmploymentRecordDto> records = employmentRecordService.getAllByUser(id);
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
    public ResponseEntity<String> saveRecord(@RequestBody EmploymentRecordDto employmentRecordDto){

        try{
            employmentRecordService.save(employmentRecordDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity< String> editRecord(@RequestBody EmploymentRecordDto employmentRecordDto){

        try{
            employmentRecordService.edit(employmentRecordDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeRecord(@PathVariable("id") Long id){

        try{
            employmentRecordService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }




}
