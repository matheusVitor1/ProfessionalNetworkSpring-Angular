package social.network.spring.domain.dtos;
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
    private String course;
    private String degree;
    private String institution;
    private String startYear;
    private String endYear;
    private String description;
    private Long userId;


}
