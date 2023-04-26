package pl.projekt.zpwj.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import pl.projekt.zpwj.model.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void givenUsername_whenFound_thenReturnUser() {
        //given
        String username = "jankowalski";

        User user = new User();
        user.setUsername(username);
        user.setRole(User.Type.USER);
        user.setEmail("jan@kowalski.pl");
        user.setPassword("haslo");
        userRepository.save(user);

        //when
        User testedUser = userRepository.findByUsername(username);

        //then
        assertThat(testedUser.getUsername()).isEqualTo(user.getUsername());
    }
}