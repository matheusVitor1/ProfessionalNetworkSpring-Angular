package social.network.spring.domain.dtos.sections;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class SectionAboutContentDto {
    private Long id;
    private String userPhoto;
    private String userEmail;
    private String jobPosition;
    private String userIntroduction;
    private String userPhone;
    private String userBirthday;
    private int userAge;
    private String cep;
    private String logradouro;
    private String bairro;
    private String city;
    private String uf;
    private Long userId;
}

