package social.network.spring.domain.entities.sections;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import social.network.spring.domain.entities.User;

@Entity
@Table
@Getter
@Setter
public class SectionAboutContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobPosition;
    @Lob
    private String userIntroduction;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public SectionAboutContent() {

    }

    public SectionAboutContent(String jobPosition, String userIntroduction, User user) {
        this.jobPosition = jobPosition;
        this.userIntroduction = userIntroduction;
        this.user = user;
    }
}
