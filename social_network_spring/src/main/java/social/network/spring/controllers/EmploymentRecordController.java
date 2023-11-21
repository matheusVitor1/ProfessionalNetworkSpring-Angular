package social.network.spring.controllers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import social.network.spring.dtos.EmploymentRecordDto;
import social.network.spring.entities.EmploymentRecord;
import social.network.spring.service.EmploymentRecordService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("EmploymentRecord")
public class EmploymentRecordController {

    private final EmploymentRecordService employmentRecordService;


    @GetMapping("/{id}")
    public ResponseEntity<List<EmploymentRecordDto>> getCustomerEmpRecord(@PathVariable("id") Long id) {
        try {
            List<EmploymentRecordDto> records = employmentRecordService.getAllByUser(id);

            if (!records.isEmpty()) {
                return new ResponseEntity<>(records, HttpStatus.OK);
            } else {-  
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/new")
    public ResponseEntity<Map<String, String>> saveRecord(@RequestBody EmploymentRecordDto employmentRecordDto){
        boolean savedRecord = employmentRecordService.save(employmentRecordDto);
        Map<String, String> response = new HashMap<>();
        if(savedRecord){
            response.put("message", "Registro profissional adicionado!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Usuário não encontrado");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Map<String, String>> editRecord(@RequestBody EmploymentRecordDto employmentRecordDto){
        boolean editedRecord = employmentRecordService.edit(employmentRecordDto);
        Map<String, String> response = new HashMap<>();
        if(editedRecord){
            response.put("message", "Registro profissional Editado");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Usuário não encontrado");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Map<String, String>> removeRecord(@PathVariable("id") Long id){
        boolean removedRecord = employmentRecordService.remove(id);
        Map<String, String> response = new HashMap<>();
        if(removedRecord){
            response.put("message", "Registro profissional Removido");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Usuário não encontrado");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }




}
