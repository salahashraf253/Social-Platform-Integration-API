package com.platform.integration.security.service;


import com.platform.integration.entity.User;
import com.platform.integration.enums.Provider;
import com.platform.integration.enums.Role;
import com.platform.integration.model.request.LoginRequestBody;
import com.platform.integration.model.request.RegisterRequestBody;
import com.platform.integration.model.response.success.LoginResponseBody;
import com.platform.integration.model.response.success.RegisterResponseBody;
import com.platform.integration.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponseBody register(RegisterRequestBody request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .provider(Provider.LOCAL)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return RegisterResponseBody.builder()
                .token(jwtToken)
                .build();
    }

    public LoginResponseBody authenticate(LoginRequestBody request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return LoginResponseBody.builder()
                .token(jwtToken)
                .build();
    }
}