package com.melodymatch.user_profil.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Basic user information.
 *
 * @author habibah
 * @version 0.1
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    /**
     * unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * user's first name.
     */
    private String firstName;

    /**
     * user's last name.
     */
    private String lastName;

    /**
     * user's pseudo.
     */
    private String pseudo;

    /**
     * user's birthday.
     */
    private LocalDate birthDate;

    /**
     * user's email.
     */
    private String email;

    /**
     * user's phone number.
     */
    private String phone;

    /**
     * user's locale language.
     */
    @Column(length = 15)
    private String locale;

    /**
     * user's latitude coordinate.
     */
    private Double latitude;

    /**
     * user's longitude coordinate.
     */
    private Double longitude;

    /**
     * updated location of the user.
     */
    @Column(name = "location_updated_at")
    private LocalDateTime locationUpdatedAt;

    /**
     * user's profile information.
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profil profil;

    /**
     * user's social connections.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserSocialMedia> socialMediaLink;
}
