package social.network.spring.domain.entities.sections;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import social.network.spring.domain.entities.User;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
public class SectionPostsContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String date;

    @ManyToOne
    private User user;
}
