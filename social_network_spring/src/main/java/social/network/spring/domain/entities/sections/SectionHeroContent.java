package social.network.spring.domain.entities.sections;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import social.network.spring.domain.entities.User;


@Entity
@Table
@Getter
@Setter
public class SectionHeroContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userWallpaper;

    private String userSelfDescription;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public SectionHeroContent(){

    }

    public SectionHeroContent(String userWallpaper, String userSelfDescription, User user) {
        this.userWallpaper = userWallpaper;
        this.userSelfDescription = userSelfDescription;
        this.user = user;
    }

}
