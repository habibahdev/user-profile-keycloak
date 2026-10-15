package com.melodymatch.user_profil.model;

import com.melodymatch.user_profil.model.enums.Gender;
import com.melodymatch.user_profil.model.enums.Orientation;
import com.melodymatch.user_profil.model.enums.Situation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Situation situation;

    @Enumerated(EnumType.STRING)
    private Orientation orientation;
}
