package com.melodymatch.user_profil.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;

    private String lastName;

    private String pseudo;
    private LocalDate birthDate;
    private String email;
    private String phone;

    @Column(length = 15)
    private String locale;

    private Double latitude;
    private Double longitude;
    private LocalDateTime locationUpdatedAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profil profil;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserSocialMedia> socialMediaLink;
}
