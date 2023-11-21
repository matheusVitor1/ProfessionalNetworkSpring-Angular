package social.network.spring.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
public class EducationalBackground {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String jobPosition;

    @Column (nullable = false)
    private String institution;

    @Column (nullable = false)
    private String startYear;

    @Column (nullable = false)
    private String endYear;

    @Column (nullable = false)
    private String description;

    @ManyToOne
    @JsonIgnoreProperties("employmentRecords")
    private User user;
}
