package social.network.spring.domain.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import social.network.spring.domain.entities.sections.SectionAboutContent;
import social.network.spring.domain.entities.sections.SectionHeroContent;

import java.util.List;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String photoUrl;

    private String userWallpaper;

    private String userSelfDescription;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String birthday;

    @Column(unique = true, nullable = false)
    private String identity;

    @Column(nullable = false)
    private boolean active;

    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "user")
    private SectionHeroContent sectionHeroContent;

    @OneToOne(mappedBy = "user")
    private Address userAddress;

    @OneToOne(mappedBy = "user")
    private SectionAboutContent sectionAboutContent;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<EmploymentRecord> employmentRecords;
    public User() {

    }

    public User(String photoUrl, String name, int age, String birthday, String identity, String email, String password, boolean active) {
        this.photoUrl = photoUrl;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.identity = identity;
        this.active = active;
        this.email = email;
        this.password = password;
    }
}
