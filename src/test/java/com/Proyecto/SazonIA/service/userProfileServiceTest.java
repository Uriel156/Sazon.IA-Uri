package com.Proyecto.SazonIA.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.Proyecto.SazonIA.model.userProfileModel;
import com.Proyecto.SazonIA.repository.userProfileRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class userProfileServiceTest {

    @MockBean
    private userProfileRepository userProfileRepository;
    
    private userProfileService userProfileService;
    
    @BeforeEach
    void setUp() {
        userProfileService = new userProfileService();
    }
    
    @Test
    void getAllUserProfilesTest() {
        // Arrange
        List<userProfileModel> expectedProfiles = Arrays.asList(new userProfileModel(), new userProfileModel());
        when(userProfileRepository.findAll()).thenReturn(expectedProfiles);
        
        // Act
        List<userProfileModel> result = userProfileService.getAllUserProfiles();
        
        // Assert
        assertEquals(expectedProfiles, result);
    }
    
    @Test
    void getUserProfileByIdTest() {
        // Arrange
        Integer userId = 1;
        userProfileModel expectedProfile = new userProfileModel();
        when(userProfileRepository.findById(userId)).thenReturn(Optional.of(expectedProfile));
        
        // Act
        Optional<userProfileModel> result = userProfileService.getUserProfileById(userId);
        
        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedProfile, result.get());
    }
} 