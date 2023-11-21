package social.network.spring.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import social.network.spring.dtos.EmploymentRecordDto;
import social.network.spring.entities.EmploymentRecord;
import social.network.spring.entities.User;
import social.network.spring.repositories.EmploymentRecordRepository;
import social.network.spring.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmploymentRecordService {

    private final EmploymentRecordRepository employmentRecordRepository;
    private final UserService userService;

    private final UserRepository userRepository;

    public EmploymentRecordService(EmploymentRecordRepository employmentRecordRepository, UserService userService, UserRepository userRepository) {
        this.employmentRecordRepository = employmentRecordRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public EmploymentRecordDto createRecordDto (EmploymentRecord employmentRecord){
        return EmploymentRecordDto.builder()
                .id(employmentRecord.getId())
                .company(employmentRecord.getCompany())
                .description(employmentRecord.getDescription())
                .endYear(employmentRecord.getEndYear())
                .startYear(employmentRecord.getStartYear())
                .jobPosition(employmentRecord.getJobPosition())
                .build();
    }

    public List<EmploymentRecordDto> getAllByUser(Long userId){

        return employmentRecordRepository.findByUser(userRepository.findById(userId)).stream()
                .map(records -> createRecordDto(records)).toList();
    }

    public boolean save (EmploymentRecordDto employmentRecordDto){
        User user = userService.findById(employmentRecordDto.getUserId());

        if (user != null){
            EmploymentRecord newRecord = new EmploymentRecord(
                    employmentRecordDto.getJobPosition(),
                    employmentRecordDto.getCompany(),
                    employmentRecordDto.getStartYear(),
                    employmentRecordDto.getEndYear(),
                    employmentRecordDto.getDescription(),
                    user);
            employmentRecordRepository.save(newRecord);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuário não encontrado");
        }
    }

    public boolean edit (EmploymentRecordDto employmentRecordDto){
        Optional<EmploymentRecord> currentRecordOpt = employmentRecordRepository.findById(employmentRecordDto.getId());
        EmploymentRecord currentRecord = currentRecordOpt.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro Profissional não encontrado com esse ID"));

        currentRecord.setCompany(employmentRecordDto.getCompany());
        currentRecord.setDescription(employmentRecordDto.getDescription());
        currentRecord.setEndYear(employmentRecordDto.getEndYear());
        currentRecord.setStartYear(currentRecord.getStartYear());
        currentRecord.setJobPosition(currentRecord.getJobPosition());
        employmentRecordRepository.save(currentRecord);
        return true;
    }

    public boolean remove (Long id){
        Optional<EmploymentRecord> currentRecordOpt = employmentRecordRepository.findById(id);
        EmploymentRecord currentRecord = currentRecordOpt.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro Profissional não encontrado com esse ID"));
        employmentRecordRepository.delete(currentRecord);
        return true;
    }


}
