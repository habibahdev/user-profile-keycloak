package com.melodymatch.user_profil.repository;

import com.melodymatch.user_profil.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testUserShouldBeFindWithEmail() {
        User user = new User();
        user.setEmail("user@api.com");
        userRepository.save(user);

        boolean exists = userRepository.existsByEmail("user@api.com");
        assertThat(exists).isTrue();
    }
}
