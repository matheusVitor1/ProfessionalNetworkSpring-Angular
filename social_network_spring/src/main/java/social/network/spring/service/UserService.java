package social.network.spring.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import social.network.spring.dtos.UserDto;
import social.network.spring.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import social.network.spring.repositories.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getALL(){
        return userRepository.findAll();
    }

    private User findById(Long id){
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


    public UserDto createNewUser(User user){
        return UserDto.builder()
                .id(user.getId())
                .photoUrl(user.getPhotoUrl())
                .name(user.getName())
                .age(user.getAge())
                .birthday(user.getBirthday())
                .identity(user.getIdentity())
                .email(user.getEmail())
                .password(user.getPassword())
                .active(user.isActive())
                .build();
    }

    public boolean saveUser(UserDto userDto) {
        List<User> users = getALL();
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());

        int age = calculateAge(userDto.getBirthday());

        if (!findByIdentity(users, userDto.getIdentity())) {
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
            User savedUser = userRepository.save(user);
            createNewUser(savedUser);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já cadastrado");
        }
    }


    private boolean updateUser (UserDto userDto){
        User userFound = findById(userDto.getId());
        userFound.setPhotoUrl(userDto.getPhotoUrl());
        userFound.setName(userDto.getName());
        userFound.setAge(userDto.getAge());
        userFound.setBirthday(userDto.getBirthday());
        userRepository.save(userFound);
        return true;
    }

    private int calculateAge(String birthDateStr) {
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
