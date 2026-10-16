package com.melodymatch.user_profil.controller;

import com.melodymatch.user_profil.model.SocialMedia;
import com.melodymatch.user_profil.model.User;
import com.melodymatch.user_profil.model.UserSocialMedia;
import com.melodymatch.user_profil.repository.SocialMediaRepository;
import com.melodymatch.user_profil.repository.UserRepository;
import com.melodymatch.user_profil.repository.UserSocialMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@RestController
public class SocialMediaController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SocialMediaRepository socialMediaRepository;

    @Autowired
    private UserSocialMediaRepository userSocialMediaRepository;

    @GetMapping("/socialmedias")
    public List<SocialMedia> socialMedias() {
        return socialMediaRepository.findAll();
    }

    @PostMapping("/users/{userId}/social-media")
    public ResponseEntity<String> create(
            @PathVariable final Long userId,
            @RequestBody final UserSocialMedia userSocialMedia
    ) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("invalid user id");
        }

        Optional<SocialMedia> socialMedia = socialMediaRepository.findById(userSocialMedia.getSocialMediaId());
        if (socialMedia.isEmpty()) {
            return ResponseEntity.badRequest().body("invalid social media id");
        }

        Optional<UserSocialMedia> link = userSocialMediaRepository.findByUserIdAndSocialMediaId(
                userId, userSocialMedia.getSocialMediaId()
        );
        if (link.isPresent()) {
            return ResponseEntity.badRequest().body("this social media link already exists for this user");
        }
        UserSocialMedia newLink = new UserSocialMedia();
        newLink.setUser(user.get());
        newLink.setSocialMedia(socialMedia.get());
        newLink.setUserId(userId);
        newLink.setSocialMediaId(socialMedia.get().getSocialMediaId());
        newLink.setProfilLink(userSocialMedia.getProfilLink());
        userSocialMediaRepository.save(newLink);
        return ResponseEntity.ok("social media link created successfully");
    }

    @PutMapping("/users/{userId}/social-media/{socialMediaId}")
    public ResponseEntity<String> update(
            @PathVariable final Long userId,
            @PathVariable final Long socialMediaId,
            @RequestBody final UserSocialMedia userSocialMedia
    ) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("invalid user id");
        }

        Optional<SocialMedia> socialMedia = socialMediaRepository.findById(socialMediaId);
        if (socialMedia.isEmpty()) {
            return ResponseEntity.badRequest().body("invalid social media id");
        }

        Optional<UserSocialMedia> link = userSocialMediaRepository.findByUserIdAndSocialMediaId(userId, socialMediaId);
        if (link.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserSocialMedia mediaLink = link.get();
        mediaLink.setProfilLink(userSocialMedia.getProfilLink());
        userSocialMediaRepository.save(mediaLink);
        return ResponseEntity.ok("social media link updated successfully");
    }
}
