package com.melodymatch.user_profil.repository;

import com.melodymatch.user_profil.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
