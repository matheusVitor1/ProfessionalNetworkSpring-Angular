package social.network.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table
@Getter
@Setter
public class UserHeroContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userWallpaper;

    private String userSelfDescription;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserHeroContent(){

    }

    public UserHeroContent(String userWallpaper, String userSelfDescription, User user) {
        this.userWallpaper = userWallpaper;
        this.userSelfDescription = userSelfDescription;
        this.user = user;
    }

}
