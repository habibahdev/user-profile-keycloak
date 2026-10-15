package com.melodymatch.user_profil.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long socialMediaId;

    private String platformName;

    @OneToMany(mappedBy = "socialMedia", cascade = CascadeType.ALL)
    private Set<UserSocialMedia> socialMediaLink;
}
