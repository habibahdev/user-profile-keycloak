package com.melodymatch.user_profil.repository;

import com.melodymatch.user_profil.model.UserSocialMedia;
import com.melodymatch.user_profil.model.UserSocialMediaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSocialMediaRepository extends JpaRepository<UserSocialMedia, UserSocialMediaId> {
    Optional<UserSocialMedia> findByUserIdAndSocialMediaId(Long userId, Long socialMediaId);
}
