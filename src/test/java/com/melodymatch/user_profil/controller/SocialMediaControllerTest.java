package com.melodymatch.user_profil.controller;

import com.melodymatch.user_profil.model.SocialMedia;
import com.melodymatch.user_profil.model.User;
import com.melodymatch.user_profil.model.UserSocialMedia;
import com.melodymatch.user_profil.repository.SocialMediaRepository;
import com.melodymatch.user_profil.repository.UserRepository;
import com.melodymatch.user_profil.repository.UserSocialMediaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class SocialMediaControllerTest {
    private MockMvc mockMvc;

    @Mock
    private SocialMediaRepository socialMediaRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserSocialMediaRepository userSocialMediaRepository;

    @InjectMocks
    private SocialMediaController socialMediaController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(socialMediaController).build();
    }

    @Test
    void testGetSocialMedias() throws Exception {
        SocialMedia socialMedia = new SocialMedia();
        socialMedia.setSocialMediaId(1L);
        socialMedia.setPlatformName("Spotify");

        when(socialMediaRepository.findAll())
                .thenReturn(List.of(socialMedia));

        mockMvc.perform(get("/socialmedias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].platformName").value("Spotify"));
    }

    @Test
    void testCreateSocialMedia() throws Exception {
        User user = new User();
        user.setUserId(1L);

        SocialMedia socialMedia = new SocialMedia();
        socialMedia.setSocialMediaId(1L);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(socialMediaRepository.findById(1L))
                .thenReturn(Optional.of(socialMedia));

        when(userSocialMediaRepository.findByUserIdAndSocialMediaId(1L, 1L))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/users/1/social-media")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "socialMediaId": 1,
                            "profileLink": "http://api.com"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().string("social media link created successfully"));
    }

    @Test
    void testCreateSocialMediaWhenLinkAlreadyExists() throws Exception {
        User user = new User();
        user.setUserId(1L);

        SocialMedia socialMedia = new SocialMedia();
        socialMedia.setSocialMediaId(1L);

        UserSocialMedia link = new UserSocialMedia();
        link.setUserId(1L);
        link.setSocialMediaId(1L);
        link.setProfilLink("http://oldlink.com");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(socialMediaRepository.findById(1L))
                .thenReturn(Optional.of(socialMedia));

        when(userSocialMediaRepository.findByUserIdAndSocialMediaId(1L, 1L))
                .thenReturn(Optional.of(link));

        mockMvc.perform(post("/users/1/social-media")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "socialMediaId": 1,
                            "profileLink": "http://newapi.com"
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("this social media link already exists for this user"));
    }

    @Test
    void testUpdateSocialMedia() throws Exception {
        User user = new User();
        user.setUserId(1L);

        SocialMedia socialMedia = new SocialMedia();
        socialMedia.setSocialMediaId(1L);

        UserSocialMedia link = new UserSocialMedia();
        link.setUserId(1L);
        link.setSocialMediaId(1L);
        link.setProfilLink("http://oldapi.com");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(socialMediaRepository.findById(1L))
                .thenReturn(Optional.of(socialMedia));

        when(userSocialMediaRepository.findByUserIdAndSocialMediaId(1L, 1L))
                .thenReturn(Optional.of(link));

        mockMvc.perform(put("/users/1/social-media/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "profileLink": "http://newapi.com"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().string("social media link updated successfully"));
    }

    @Test
    void testUpdatedSocialMediaWHenLinkNotFound() throws Exception {
        User user = new User();
        user.setUserId(1L);

        SocialMedia socialMedia = new SocialMedia();
        socialMedia.setSocialMediaId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        when(socialMediaRepository.findById(1L)).thenReturn(Optional.of(socialMedia));

        when(userSocialMediaRepository.findByUserIdAndSocialMediaId(1L, 1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/users/1/social-media/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "profileLink": "http://newapi.com"
                        }
                        """))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateSocialMediaWhenUserIdIsInvalid() throws Exception {
        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/users/1/social-media")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "socialMediaId": 1,
                            "profileLink": "http://api.com"
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("invalid user id"));
    }

    @Test
    void testCreateSocialMediaWhenInvalidSocialMediaId() throws Exception {
        User user = new User();
        user.setUserId(1L);

        SocialMedia socialMedia = new SocialMedia();
        socialMedia.setSocialMediaId(1L);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(socialMediaRepository.findById(1L))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/users/1/social-media")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "socialMediaId": 1,
                            "profileLink": "http://api.com"
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("invalid social media id"));
    }
}
