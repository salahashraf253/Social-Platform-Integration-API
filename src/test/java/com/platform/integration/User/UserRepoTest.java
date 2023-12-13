package com.platform.integration.User;

import com.platform.integration.entity.User;
import com.platform.integration.enums.Provider;
import com.platform.integration.enums.Role;
import com.platform.integration.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class UserRepoTest extends BasicTest {

    @Autowired
    private UserRepo userRepo;


    @Test
    public void testFindByEmailForValidEmail() {
        String userEmail = "test@example.com";
        var user = User.builder()
                .firstName("mock name")
                .lastName("mock last name")
                .email(userEmail)
                .password("********")
                .role(Role.USER)
                .provider(Provider.LOCAL)
                .build();

        userRepo.save(user);

        Optional<User> foundUser = userRepo.findByEmail(userEmail);
        assertThat(foundUser.isPresent()).isEqualTo(true);

        User savedUser = foundUser.get();
        assertThat(savedUser.getEmail()).isEqualTo(userEmail);
        assertThat(savedUser.getFirstName()).isEqualTo(user.getFirstName());
    }

    @Test
    public void testFindByEmailForNonValidEmail() {
        String nonValidEmail = "salah@gmail.com";
        Optional<User> user = userRepo.findByEmail(nonValidEmail);
        assertThat(user.isPresent()).isEqualTo(false);

    }
}
