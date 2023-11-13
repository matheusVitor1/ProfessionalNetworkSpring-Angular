package social.network.spring.dtos;
import lombok.*;
import social.network.spring.entities.User;
@Data
@Builder
@Getter
@Setter
public class UserHeroContentDto {
    private Long id;
    private String userPhoto;
    private String userNickName;
    private String userWallpaper;
    private String userSelfDescription;
    private String textColor;
    private Long userId;
}
