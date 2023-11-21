package social.network.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class UserAboutContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobPosition;
    @Lob
    private String userIntroduction;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserAboutContent() {

    }

    public UserAboutContent(String jobPosition, String userIntroduction, User user) {
        this.jobPosition = jobPosition;
        this.userIntroduction = userIntroduction;
        this.user = user;
    }
}
