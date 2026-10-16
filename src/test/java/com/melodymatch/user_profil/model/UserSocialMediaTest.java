package com.melodymatch.user_profil.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserSocialMediaTest {
    @Test
    void testUserSocialMediaShouldBeEqualsAndHaveEqualsHashCode() {
        UserSocialMedia userSocialMedia1 = new UserSocialMedia();
        userSocialMedia1.setUserId(1L);
        userSocialMedia1.setSocialMediaId(2L);

        UserSocialMedia userSocialMedia2 = new UserSocialMedia();
        userSocialMedia2.setUserId(1L);
        userSocialMedia2.setSocialMediaId(2L);

        assertThat(userSocialMedia1).isEqualTo(userSocialMedia2);
        assertThat(userSocialMedia1.hashCode()).isEqualTo(userSocialMedia2.hashCode());
    }

    @Test
    void testUserSocialMediaShouldGetterAndSetterOK() {
        UserSocialMedia userSocialMedia = new UserSocialMedia();
        userSocialMedia.setUserId(1L);
        userSocialMedia.setSocialMediaId(2L);
        userSocialMedia.setProfilLink("http://api.com");

        assertThat(userSocialMedia.getUserId()).isEqualTo(1L);
        assertThat(userSocialMedia.getSocialMediaId()).isEqualTo(2L);
        assertThat(userSocialMedia.getProfilLink()).isEqualTo("http://api.com");
    }

    @Test
    void testUserSocialMediaShouldNotBeEqualsWithAnOtherObject() {
        UserSocialMedia userSocialMedia1 = new UserSocialMedia();
        userSocialMedia1.setUserId(1L);
        userSocialMedia1.setSocialMediaId(2L);

        UserSocialMedia userSocialMedia2 = new UserSocialMedia();
        userSocialMedia2.setUserId(3L);
        userSocialMedia2.setSocialMediaId(4L);

        assertThat(userSocialMedia1).isNotEqualTo(userSocialMedia2);
    }
}
