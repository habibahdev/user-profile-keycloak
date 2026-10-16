package com.melodymatch.user_profil.model;

import com.melodymatch.user_profil.model.enums.Gender;
import com.melodymatch.user_profil.model.enums.Orientation;
import com.melodymatch.user_profil.model.enums.Situation;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Additional personal information.
 *
 * @author habibah
 * @version 0.1
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Information {
    /**
     * unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * user's age.
     */
    private Integer age;

    /**
     * user's gender.
     */
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * user's relationship status.
     */
    @Enumerated(EnumType.STRING)
    private Situation situation;

    /**
     * user's sexual orientation.
     */
    @Enumerated(EnumType.STRING)
    private Orientation orientation;
}
