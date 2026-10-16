package com.melodymatch.user_profil.controller;

import com.melodymatch.user_profil.model.Information;
import com.melodymatch.user_profil.model.Profil;
import com.melodymatch.user_profil.model.User;
import com.melodymatch.user_profil.model.enums.Gender;
import com.melodymatch.user_profil.repository.InformationRepository;
import com.melodymatch.user_profil.repository.ProfilRepository;
import com.melodymatch.user_profil.repository.UserRepository;
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
public class ProfilControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ProfilRepository profilRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private InformationRepository informationRepository;

    @InjectMocks
    private ProfilController profilController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(profilController).build();
    }

    @Test
    void testCreateProfile() throws Exception {
        User user = new User();
        user.setUserId(1L);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(profilRepository.findByUser_UserId(1L))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/users/1/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "biography": "new bio",
                            "photo": "photo.png"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().string("profile created successfully"));
    }

    @Test
    void testCreateProfile_AlreadyExists() throws Exception {
        User user = new User();
        user.setUserId(1L);

        Profil profil = new Profil();
        profil.setId(1L);
        profil.setBiography("existing bio");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(profilRepository.findByUser_UserId(1L))
                .thenReturn(Optional.of(profil));

        mockMvc.perform(post("/users/1/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "biography": "new bio",
                            "photo": "photo.png"
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("this user already has a profile"));
    }

    @Test
    void testUpdateProfile() throws Exception {
        User user = new User();
        user.setUserId(1L);

        Profil profil = new Profil();
        profil.setId(1L);
        profil.setBiography("old bio");
        profil.setPhoto("oldphoto.png");
        profil.setUser(user);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(profilRepository.findByUser_UserId(1L))
                .thenReturn(Optional.of(profil));

        mockMvc.perform(put("/users/1/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "biography": "updated bio",
                            "photo": "updatedphoto.png"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().string("profile updated successfully"));
    }

    @Test
    void testUpdatedProfileWhenProfileNotFound() throws Exception {
        User user = new User();
        user.setUserId(1L);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(profilRepository.findByUser_UserId(1L))
                .thenReturn(Optional.empty());

        mockMvc.perform(put("/users/1/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "biography": "updated bio",
                            "photo": "updatedphoto.png"
                        }
                        """))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateProfileWhenUserIdIsInvalid() throws Exception {
        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/users/1/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "biography": "new bio",
                            "photo": "photo.png"
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("invalid user id"));
    }

    @Test
    void testGetAllProfiles() throws Exception {
        Profil profil = new Profil();
        profil.setId(1L);
        profil.setBiography("test bio");

        when(profilRepository.findAll())
                .thenReturn(List.of(profil));

        mockMvc.perform(get("/profiles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].biography").value("test bio"));
    }

    @Test
    void testGetAllInformations() throws Exception {
        Information information = new Information();
        information.setAge(23);
        information.setGender(Gender.MAN);

        when(informationRepository.findAll())
                .thenReturn(List.of(information));

        mockMvc.perform(get("/informations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].age").value(23))
                .andExpect(jsonPath("$[0].gender").value("MAN"));
    }
}
