package com.melodymatch.user_profil.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSocialMediaId implements Serializable {
    private Long userId;

    private Long socialMediaId;

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        UserSocialMediaId that = (UserSocialMediaId) object;
        return userId.equals(that.userId) && socialMediaId.equals(that.socialMediaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, socialMediaId);
    }
}
