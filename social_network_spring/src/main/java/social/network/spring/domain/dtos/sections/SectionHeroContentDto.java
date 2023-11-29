package social.network.spring.domain.dtos.sections;
import lombok.*;

@Data
@Builder
@Getter
@Setter
public class SectionHeroContentDto {
    private String userPhoto;
    private String userNickName;
    private String userWallpaper;
    private String userSelfDescription;
    private Long userId;
}
