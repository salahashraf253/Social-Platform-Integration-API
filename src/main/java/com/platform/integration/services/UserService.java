package com.platform.integration.services;

import com.platform.integration.entity.User;
import com.platform.integration.enums.Provider;
import com.platform.integration.enums.Role;
import com.platform.integration.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public void processOAuthPostLogin(String name, String email, Provider provider) {
        Optional<User> existUser = userRepo.findByEmail(email);

        if (existUser.isEmpty()) {
            var user = User.builder()
                    .email(email)
                    .firstName(name)
                    .provider(provider)
                    .role(Role.USER)
                    .build();

            userRepo.save(user);
        }

    }


}
