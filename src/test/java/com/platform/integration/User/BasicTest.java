package com.platform.integration.User;

import com.platform.integration.repository.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicTest {

    @Autowired
    protected UserRepo userRepo;

    @AfterEach
    void tearDown() {
        userRepo.deleteAll();
    }
}
