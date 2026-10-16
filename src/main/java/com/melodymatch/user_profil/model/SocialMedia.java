package com.melodymatch.user_profil.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Social media platform.
 *
 * @author habibah
 * @version 0.1
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialMedia {
    /**
     * unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long socialMediaId;

    /**
     * platform's name.
     */
    private String platformName;

    /**
     * users who have connected with this platform.
     */
    @OneToMany(mappedBy = "socialMedia", cascade = CascadeType.ALL)
    private Set<UserSocialMedia> socialMediaLink;
}
