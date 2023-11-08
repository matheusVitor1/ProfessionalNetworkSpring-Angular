package social.network.spring.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class UserDto {
    private long id;

    private String photoUrl;

    private String name;

    private int age;

    private String birthday;

    private String identity;

    private boolean active;

    private String email;

    private String password;
}
