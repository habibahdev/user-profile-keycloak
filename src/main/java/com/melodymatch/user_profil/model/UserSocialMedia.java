package com.melodymatch.user_profil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Join table class between User table and SocialMedia table.
 *
 * @author habibah
 * @version 0.1
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UserSocialMediaId.class)
public class UserSocialMedia {
    /**
     * composite key.
     */
    @Id
    private Long userId;

    /**
     * composite key.
     */
    @Id
    private Long socialMediaId;

    /**
     * user's profile url on social media.
     */
    private String profilLink;

    /**
     * owner of the social media connection.
     */
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    /**
     * social media platform this connection refers to.
     */
    @ManyToOne
    @MapsId("socialMediaId")
    @JoinColumn(name = "social_media_id")
    @JsonIgnore
    private SocialMedia socialMedia;

    /**
     * custom equals method.
     * @param object the object to compare with.
     * @return true if the objects are equals, false otherwise.
     */
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

    /**
     * custom hashcode method.
     * @return the hash based on the composite key.
     */
    @Override
    public int hashCode() {
        return Objects.hash(userId, socialMediaId);
    }
}
