package social.network.spring.dtos;
import lombok.*;

@Data
@Builder
@Getter
@Setter
public class UserHeroContentDto {
    private String userPhoto;
    private String userNickName;
    private String userWallpaper;
    private String userSelfDescription;
    private Long userId;
}
