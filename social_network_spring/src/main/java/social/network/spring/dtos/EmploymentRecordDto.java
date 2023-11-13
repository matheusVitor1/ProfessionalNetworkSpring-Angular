package social.network.spring.dtos;
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
    private String position;
    private String company;
    private String startDate;
    private String endDate;
    private String description;
    private Long userId;

}
