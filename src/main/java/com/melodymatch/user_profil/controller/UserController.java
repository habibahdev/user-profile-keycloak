package com.melodymatch.user_profil.controller;

import com.melodymatch.user_profil.model.User;
import com.melodymatch.user_profil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    public String getProtectedRoute() {
        return "protected route";
    }

    @GetMapping("/public")
    public String getPublicRoute() {
        return "public route";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<User> create(
            @RequestBody User user,
            @RequestHeader(value = "Accept-Language", required = false) String acceptLanguage
    ) {
        if (user.getLocale() == null || user.getLocale().isEmpty()) {
            user.setLocale(extractAcceptLanguageFromRequestHeader(acceptLanguage));
        }
        if (user.getLatitude() != null && user.getLongitude() != null) {
            user.setLocationUpdatedAt(LocalDateTime.now());
        }
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/users/{userId}/locale")
    public ResponseEntity<User> updateLocale(
            @PathVariable Long userId,
            @RequestBody String locale
    ) {
        return userRepository.findById(userId)
                .map(user -> {
                    String escapeLocale = locale.replaceAll("^\"|\"$", "");
                    user.setLocale(escapeLocale);
                    User updatedUser = userRepository.save(user);
                    return ResponseEntity.ok(updatedUser);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/users/{userId}/locale")
    public ResponseEntity<String> getLocale(@PathVariable Long userId) {
        return userRepository.findById(userId)
                .map(user -> ResponseEntity.ok(user.getLocale()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/users/{userId}/location")
    public ResponseEntity<User> updateLocation(
            @PathVariable Long userId,
            @RequestBody Map<String, Double> data
    ) {
        Double latitude = data.get("latitude");
        Double longitude = data.get("longitude");
        if (latitude == null || longitude == null) {
            return ResponseEntity.badRequest().build();
        }
        return userRepository.findById(userId)
                .map(user -> {
                    user.setLongitude(longitude);
                    user.setLatitude(latitude);
                    user.setLocationUpdatedAt(LocalDateTime.now());
                    User updatedUser = userRepository.save(user);
                    return ResponseEntity.ok(updatedUser);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/users/{userId}/location")
    public ResponseEntity<Map<String, Object>> getLocation(@PathVariable Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    Map<String, Object> data = Map.of(
                            "latitude", user.getLatitude(),
                            "longitude", user.getLongitude(),
                            "locationUpdatedAt", user.getLocationUpdatedAt()
                    );
                    return ResponseEntity.ok(data);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private String extractAcceptLanguageFromRequestHeader(String acceptLanguage) {
        if (acceptLanguage == null || acceptLanguage.isEmpty()) {
            return "en";
        }
        String[] languages = acceptLanguage.split(",");
        if (languages.length > 0) {
            String lang = languages[0].trim();
            if (lang.contains(";")) {
                lang = lang.substring(0, lang.indexOf(";")).trim();
            }
            return lang;
        }
        return "en";
    }
}
