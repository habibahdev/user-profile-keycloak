package com.melodymatch.user_profil.repository;

import com.melodymatch.user_profil.model.Profil;
import com.melodymatch.user_profil.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
public class ProfilRepositoryTest {
    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testProfilUserShouldBeFoundById() {
        User user = new User();
        user.setEmail("user@api.com");

        User savedUser = userRepository.save(user);

        Profil profil = new Profil();
        profil.setUser(savedUser);

        profilRepository.save(profil);

        Optional<Profil> foundProfil = profilRepository.findByUser_UserId(savedUser.getUserId());
        assertThat(foundProfil).isPresent();
        assertThat(foundProfil.get().getUser().getUserId()).isEqualTo(savedUser.getUserId());
    }
}
