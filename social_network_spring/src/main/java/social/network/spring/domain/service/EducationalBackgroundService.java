package social.network.spring.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import social.network.spring.domain.dtos.EducationalBackgroundDto;
import social.network.spring.domain.dtos.EmploymentRecordDto;
import social.network.spring.domain.entities.EducationalBackground;
import social.network.spring.domain.entities.EmploymentRecord;
import social.network.spring.domain.entities.User;
import social.network.spring.infra.gateway.repositories.EducationalBackgroundRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EducationalBackgroundService {
    private final UserService userService;
    private final EducationalBackgroundRepository educationalBackgroundRepository;


    public EducationalBackgroundService(UserService userService, EducationalBackgroundRepository educationalBackgroundRepository) {
        this.userService = userService;
        this.educationalBackgroundRepository = educationalBackgroundRepository;
    }

    public EducationalBackgroundDto createRecordDto(EducationalBackground educationalBackground){
        return EducationalBackgroundDto.builder()
                .id(educationalBackground.getId())
                .degree(educationalBackground.getDegree())
                .description(educationalBackground.getDescription())
                .endYear(educationalBackground.getEndYear())
                .startYear(educationalBackground.getStartYear())
                .userId(educationalBackground.getUser().getId())
                .build();
    }

    public List<EducationalBackgroundDto> getAllByUser(Long userId){
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID do Usuário não pode ser nulo");
        }
        return educationalBackgroundRepository.findByUserId(userId).stream()
                .map(records -> createRecordDto(records)).toList();
    }

    public boolean save(EducationalBackgroundDto educationalBackgroundDto){

        if (educationalBackgroundDto.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID do Usuário não pode ser nulo");
        }

        User user = userService.findById(educationalBackgroundDto.getUserId());
        if (user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuário não encontrado");
        }
        EducationalBackground newRecord = new EducationalBackground(
                educationalBackgroundDto.getDegree(),
                educationalBackgroundDto.getInstitution(),
                educationalBackgroundDto.getStartYear(),
                educationalBackgroundDto.getEndYear(),
                educationalBackgroundDto.getDescription(),
                user
        );
        educationalBackgroundRepository.save(newRecord);
        return true;
    }

    public boolean edit(EducationalBackgroundDto educationalBackgroundDto) {

        if (educationalBackgroundDto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID do Registro Profissional não pode ser nulo");
        }

        Optional<EducationalBackground> currentRecordOpt = educationalBackgroundRepository.findById(educationalBackgroundDto.getId());
        EducationalBackground currentRecord = currentRecordOpt.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro Educacional não encontrado com esse ID"));

        currentRecord.setDegree(educationalBackgroundDto.getDegree());
        currentRecord.setDescription(educationalBackgroundDto.getDescription());
        currentRecord.setEndYear(educationalBackgroundDto.getEndYear());
        currentRecord.setStartYear(educationalBackgroundDto.getStartYear());
        currentRecord.setInstitution(educationalBackgroundDto.getInstitution());
        educationalBackgroundRepository.save(currentRecord);
        return true;
    }

    public boolean remove (Long id){
        if (id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID do Registro Profissional não pode ser nulo");
        }
        Optional<EducationalBackground> currentRecordOpt = educationalBackgroundRepository.findById(id);
        EducationalBackground currentRecord = currentRecordOpt.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro Profissional não encontrado com esse ID"));
        educationalBackgroundRepository.delete(currentRecord);
        return true;
    }
}
