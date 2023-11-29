package social.network.spring.domain.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import social.network.spring.domain.dtos.EmploymentRecordDto;
import social.network.spring.domain.entities.EmploymentRecord;
import social.network.spring.domain.entities.User;
import social.network.spring.infra.gateway.repositories.EmploymentRecordRepository;


import java.util.List;
import java.util.Optional;

@Service
public class EmploymentRecordService {

    private final EmploymentRecordRepository employmentRecordRepository;
    private final UserService userService;

    public EmploymentRecordService(EmploymentRecordRepository employmentRecordRepository, UserService userService) {
        this.employmentRecordRepository = employmentRecordRepository;
        this.userService = userService;
    }

    public EmploymentRecordDto createRecordDto (EmploymentRecord employmentRecord){
        return EmploymentRecordDto.builder()
                .id(employmentRecord.getId())
                .company(employmentRecord.getCompany())
                .description(employmentRecord.getDescription())
                .endYear(employmentRecord.getEndYear())
                .startYear(employmentRecord.getStartYear())
                .jobPosition(employmentRecord.getJobPosition())
                .userId(employmentRecord.getUser().getId())
                .build();
    }

    public List<EmploymentRecordDto> getAllByUser(Long userId){
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID do Usuário não pode ser nulo");
        }

        return employmentRecordRepository.findByUserId(userId).stream()
                .map(records -> createRecordDto(records)).toList();
    }

    public boolean save (EmploymentRecordDto employmentRecordDto){

        if (employmentRecordDto.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID do Usuário não pode ser nulo");
        }

        User user = userService.findById(employmentRecordDto.getUserId());
        if (user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuário não encontrado");
        }


        EmploymentRecord newRecord = new EmploymentRecord(
                employmentRecordDto.getJobPosition(),
                employmentRecordDto.getCompany(),
                employmentRecordDto.getStartYear(),
                employmentRecordDto.getEndYear(),
                employmentRecordDto.getDescription(),
                user);
            employmentRecordRepository.save(newRecord);
            return true;

    }

    public boolean edit(EmploymentRecordDto employmentRecordDto) {

        if (employmentRecordDto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID do Registro Profissional não pode ser nulo");
        }

        Optional<EmploymentRecord> currentRecordOpt = employmentRecordRepository.findById(employmentRecordDto.getId());
        EmploymentRecord currentRecord = currentRecordOpt.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro Profissional não encontrado com esse ID"));

        currentRecord.setCompany(employmentRecordDto.getCompany());
        currentRecord.setDescription(employmentRecordDto.getDescription());
        currentRecord.setEndYear(employmentRecordDto.getEndYear());
        currentRecord.setStartYear(employmentRecordDto.getStartYear());
        currentRecord.setJobPosition(employmentRecordDto.getJobPosition());
        employmentRecordRepository.save(currentRecord);
        return true;
    }


    public boolean remove (Long id){
        if (id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID do Registro Profissional não pode ser nulo");
        }

        Optional<EmploymentRecord> currentRecordOpt = employmentRecordRepository.findById(id);
        EmploymentRecord currentRecord = currentRecordOpt.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro Profissional não encontrado com esse ID"));
        employmentRecordRepository.delete(currentRecord);
        return true;
    }


}
