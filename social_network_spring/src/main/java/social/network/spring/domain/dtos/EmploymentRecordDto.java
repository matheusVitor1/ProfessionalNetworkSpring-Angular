package social.network.spring.domain.dtos;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Builder
@Getter
@Setter
public class EmploymentRecordDto {
    private Long id;
    private String jobPosition;
    private String company;
    private String startYear;
    private String endYear;
    private String description;
    private Long userId;

}
