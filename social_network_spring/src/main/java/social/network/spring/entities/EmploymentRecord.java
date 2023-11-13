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
    private String position;

    @Column (nullable = false)
    private String company;

    @Column (nullable = false)
    private String startDate;

    @Column (nullable = false)
    private String endDate;

    @Column (nullable = false)
    private String description;

    @ManyToOne
    private User user;
}
