package com.melodymatch.user_profil.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserSocialMediaIdTest {
    @Test
    void testUserSocialMediaIdShouldBeEqualsAndHaveEqualsHashCode() {
        UserSocialMediaId userSocialMediaId1 = new UserSocialMediaId();
        userSocialMediaId1.setUserId(1L);
        userSocialMediaId1.setSocialMediaId(2L);

        UserSocialMediaId userSocialMediaId2 = new UserSocialMediaId();
        userSocialMediaId2.setUserId(1L);
        userSocialMediaId2.setSocialMediaId(2L);

        assertThat(userSocialMediaId1).isEqualTo(userSocialMediaId2);
        assertThat(userSocialMediaId1.hashCode()).isEqualTo(userSocialMediaId2.hashCode());
    }

    @Test
    void testUserSocialMediaShouldGetterAndSetterOK() {
        UserSocialMediaId userSocialMediaId = new UserSocialMediaId();
        userSocialMediaId.setUserId(1L);
        userSocialMediaId.setSocialMediaId(2L);

        assertThat(userSocialMediaId.getUserId()).isEqualTo(1L);
        assertThat(userSocialMediaId.getSocialMediaId()).isEqualTo(2L);
    }

    @Test
    void testUserSocialMediaShouldNotBeEqualsWithAnOtherObject() {
        UserSocialMediaId userSocialMediaId1 = new UserSocialMediaId();
        userSocialMediaId1.setUserId(1L);
        userSocialMediaId1.setSocialMediaId(2L);

        UserSocialMediaId userSocialMediaId2 = new UserSocialMediaId();
        userSocialMediaId2.setUserId(3L);
        userSocialMediaId2.setSocialMediaId(4L);

        assertThat(userSocialMediaId1).isNotEqualTo(userSocialMediaId2);
    }
}
