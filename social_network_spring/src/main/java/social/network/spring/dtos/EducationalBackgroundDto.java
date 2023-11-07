package social.network.spring.dtos;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Builder
@Getter
@Setter
public class EducationalBackgroundDto {

    private Long id;
    private String degree;
    private String institution;
    private String major;
    private String startDate;
    private String endDate;
    private String description;
    private Long userId;


}
