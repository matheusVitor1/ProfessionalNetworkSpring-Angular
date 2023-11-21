package social.network.spring.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
public class EmploymentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String jobPosition;

    @Column (nullable = false)
    private String company;

    @Column (nullable = false)
    private String startYear;

    @Column (nullable = false)
    private String endYear;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public EmploymentRecord() {

    }

    public EmploymentRecord(String jobPosition, String company, String startYear, String endYear, String description, User user) {
        this.jobPosition = jobPosition;
        this.company = company;
        this.startYear = startYear;
        this.endYear = endYear;
        this.description = description;
        this.user = user;
    }
}
