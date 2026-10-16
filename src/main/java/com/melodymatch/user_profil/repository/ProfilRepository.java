package com.melodymatch.user_profil.repository;

import com.melodymatch.user_profil.model.Profil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfilRepository extends JpaRepository<Profil, Long> {
    Optional<Profil> findByUser_UserId(Long userId);
}
