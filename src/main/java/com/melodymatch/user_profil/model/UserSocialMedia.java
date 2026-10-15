package com.melodymatch.user_profil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UserSocialMediaId.class)
public class UserSocialMedia {
    @Id
    private Long userId;

    @Id
    private Long socialMediaId;

    private String profilLink;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @MapsId("socialMediaId")
    @JoinColumn(name = "social_media_id")
    @JsonIgnore
    private SocialMedia socialMedia;

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        UserSocialMedia that = (UserSocialMedia) object;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(socialMediaId, that.socialMediaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, socialMediaId);
    }
}
