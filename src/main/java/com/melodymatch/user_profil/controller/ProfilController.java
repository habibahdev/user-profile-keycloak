package com.melodymatch.user_profil.controller;

import com.melodymatch.user_profil.model.Information;
import com.melodymatch.user_profil.model.Profil;
import com.melodymatch.user_profil.model.User;
import com.melodymatch.user_profil.repository.InformationRepository;
import com.melodymatch.user_profil.repository.ProfilRepository;
import com.melodymatch.user_profil.repository.UserRepository;
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
public class ProfilController {
    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private InformationRepository informationRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profiles")
    public List<Profil> profiles() {
        return profilRepository.findAll();
    }

    @GetMapping("/informations")
    public List<Information> informations() {
        return informationRepository.findAll();
    }

    @PostMapping("/users/{userId}/profile")
    public ResponseEntity<String> create(
            @PathVariable final Long userId,
            @RequestBody final Profil profilRequest
    ) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("invalid user id");
        }

        Optional<Profil> profil = profilRepository.findByUser_UserId(userId);
        if (profil.isPresent()) {
            return ResponseEntity.badRequest().body("this user already has a profile");
        }

        User u = user.get();
        profilRequest.setUser(u);
        if (profilRequest.getInformation() != null) {
            Information information = profilRequest.getInformation();
            informationRepository.save(information);
            profilRequest.setInformation(information);
        }
        profilRepository.save(profilRequest);
        return ResponseEntity.ok("profile created successfully");
    }

    @PutMapping("/users/{userId}/profile")
    public ResponseEntity<String> update(
            @PathVariable final Long userId,
            @RequestBody final Profil profilRequest
    ) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("invalid user id");
        }

        Optional<Profil> profil = profilRepository.findByUser_UserId(userId);
        if (profil.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Profil p = profil.get();
        p.setBiography(profilRequest.getBiography());
        p.setPhoto(profilRequest.getPhoto());
        if (profilRequest.getInformation() != null) {
            Information information = profilRequest.getInformation();
            if (p.getInformation() != null) {
                Information info = p.getInformation();
                info.setAge(information.getAge());
                info.setGender(information.getGender());
                info.setSituation(information.getSituation());
                info.setOrientation(information.getOrientation());
            } else {
                informationRepository.save(information);
                p.setInformation(information);
            }
        }
        profilRepository.save(p);
        return ResponseEntity.ok("profile updated successfully");
    }
}
