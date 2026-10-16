package com.melodymatch.user_profil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User profile.
 *
 * @author habibah
 * @version 0.1
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profil {
    /**
     * unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * path to the user's profile photo.
     */
    private String photo;

    /**
     * user's biography.
     */
    private String biography;

    /**
     * the user associated to the profile.
     */
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    /**
     * additional personal information associated to the profile.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "information_id")
    private Information information;
}
