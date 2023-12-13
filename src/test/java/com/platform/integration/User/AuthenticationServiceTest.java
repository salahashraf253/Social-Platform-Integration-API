package com.platform.integration.User;

import com.platform.integration.entity.User;
import com.platform.integration.enums.Provider;
import com.platform.integration.enums.Role;
import com.platform.integration.model.request.LoginRequestBody;
import com.platform.integration.model.request.RegisterRequestBody;
import com.platform.integration.model.response.success.RegisterResponseBody;
import com.platform.integration.security.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class AuthenticationServiceTest extends BasicTest {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testUserCanRegister() {
        var requestBody = RegisterRequestBody.builder()
                .firstName("mock name")
                .lastName("mock last name")
                .email("test@gmail.com")
                .password("********")
                .build();

        RegisterResponseBody responseBody = authService.register(requestBody);

        assertThat(responseBody.getToken()).isNotNull();

    }


    void createUser() {
        User testUser = User.builder()
                .firstName("Test")
                .lastName("User")
                .email("test@example.com")
                .password(passwordEncoder.encode("encodedPassword"))
                .role(Role.USER)
                .provider(Provider.LOCAL)
                .build();
        userRepo.save(testUser);
    }

    @Test
    public void testUserCanLoginWithValidEmailAndPassword() {
        createUser();
        LoginRequestBody loginRequestBody = new LoginRequestBody();
        loginRequestBody.setEmail("test@example.com");
        loginRequestBody.setPassword("encodedPassword");

        var loginResponseBody = authService.authenticate(loginRequestBody);
        assertNotNull(loginResponseBody);
    }


    @Test
    public void testUserCanLoginWithInvalidEmailAndPassword() {
        LoginRequestBody loginRequestBody = new LoginRequestBody();
        loginRequestBody.setEmail("test@example.com");
        loginRequestBody.setPassword("encodedPassword");

        assertThrows(AuthenticationException.class, () -> authService.authenticate(loginRequestBody));
    }


}
