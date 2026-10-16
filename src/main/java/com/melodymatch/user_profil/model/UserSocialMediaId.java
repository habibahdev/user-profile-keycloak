package com.melodymatch.user_profil.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * Composite primary key class.
 *
 * @author habibah
 * @version 0.1
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSocialMediaId implements Serializable {
    /**
     * composite key.
     */
    private Long userId;

    /**
     * composite key.
     */
    private Long socialMediaId;

    /**
     * custom equals method
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
        UserSocialMediaId that = (UserSocialMediaId) object;
        return userId.equals(that.userId) && socialMediaId.equals(that.socialMediaId);
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
