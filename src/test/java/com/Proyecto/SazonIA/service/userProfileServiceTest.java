package com.Proyecto.SazonIA.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.Proyecto.SazonIA.model.UserProfileModel;
import com.Proyecto.SazonIA.repository.userProfileRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class userProfileServiceTest {

    @MockBean
    private userProfileRepository userProfileRepository;
    
    private UserProfileService userProfileService;
    
    @BeforeEach
    void setUp() {
        userProfileService = new UserProfileService();
    }
    
    @Test
    void getAllUserProfilesTest() {
        // Arrange
        List<UserProfileModel> expectedProfiles = Arrays.asList(new UserProfileModel(), new UserProfileModel());
        when(userProfileRepository.findAll()).thenReturn(expectedProfiles);
        
        // Act
        List<UserProfileModel> result = userProfileService.getAllUserProfiles();
        
        // Assert
        assertEquals(expectedProfiles, result);
    }
    
    @Test
    void getUserProfileByIdTest() {
        // Arrange
        Integer userId = 1;
        UserProfileModel expectedProfile = new UserProfileModel();
        when(userProfileRepository.findById(userId)).thenReturn(Optional.of(expectedProfile));
        
        // Act
        Optional<UserProfileModel> result = userProfileService.getUserProfileById(userId);
        
        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedProfile, result.get());
    }
} 