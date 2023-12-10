package social.network.spring.domain.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import social.network.spring.domain.dtos.UserAuthDto;
import social.network.spring.domain.dtos.UserDto;
import social.network.spring.domain.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import social.network.spring.infra.gateway.repositories.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
// teste

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserAuthDto createAuthenticatedUserDto(User user) {

        return UserAuthDto.builder()
                .id(user.getId())
                .photoUrl(user.getPhotoUrl())
                .name(user.getName())
                .age(user.getAge())
                .birthday(user.getBirthday())
                .identity(user.getIdentity())
                .email(user.getEmail())
                .build();
    }

    public List<User> getALL(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return this.userRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Usuário não encontrado"
                        )
                );
    }
    private boolean findByIdentity(List<User> users, String identidade) {
        return users.stream().anyMatch(user -> user.getIdentity().equals(identidade));
    }

    private boolean findByEmail(List<User> users, String email) {
        return users.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    public UserAuthDto saveUser(UserDto userDto) {
        List<User> users = getALL();
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());

        int age = calculateAge(userDto.getBirthday());

        if (!findByIdentity(users, userDto.getIdentity()) && !findByEmail(users, userDto.getEmail())) {
            User user = new User(
                    userDto.getPhotoUrl(),
                    userDto.getName(),
                    age,
                    userDto.getBirthday(),
                    userDto.getIdentity(),
                    userDto.getEmail(),
                    hashedPassword,
                    userDto.isActive()
            );
            userRepository.save(user);
            return createAuthenticatedUserDto(user);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já cadastrado");
        }
    }


    public boolean updateUser (UserDto userDto){
        User userFound = findById(userDto.getId());
        userFound.setPhotoUrl(userDto.getPhotoUrl());
        userFound.setName(userDto.getName());
        userFound.setAge(userDto.getAge());
        userFound.setBirthday(userDto.getBirthday());
        userRepository.save(userFound);
        return true;
    }

    public int calculateAge(String birthDateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate;
        try {
            birthDate = dateFormat.parse(birthDateStr);
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de nascimento inválida");
        }

        Calendar dob = Calendar.getInstance();
        dob.setTime(birthDate);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)
                || (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
                && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return age;
    }



    
}
