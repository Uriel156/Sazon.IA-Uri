package com.Proyecto.SazonIA.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.Proyecto.SazonIA.model.UserProfileModel;
import com.Proyecto.SazonIA.service.UserProfileService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class userProfileControllerTest {

    @MockBean
    private UserProfileService userProfileService;
    
    private UserProfileController controller;
    
    @BeforeEach
    void setUp() {
        controller = new UserProfileController();
    }
    
    @Test
    void getAllUserProfilesTest() {
        // Arrange
        List<UserProfileModel> expectedProfiles = Arrays.asList(new UserProfileModel(), new UserProfileModel());
        when(userProfileService.getAllUserProfiles()).thenReturn(expectedProfiles);
        
        // Act
        ResponseEntity<List<UserProfileModel>> response = controller.getAllUserProfiles();
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedProfiles, response.getBody());
    }
    
    @Test
    void getUserProfileByIdTest() {
        // Arrange
        Integer userId = 1;
        UserProfileModel expectedProfile = new UserProfileModel();
        when(userProfileService.getUserProfileById(userId)).thenReturn(Optional.of(expectedProfile));
        
        // Act
        ResponseEntity<UserProfileModel> response = controller.getUserProfileById(userId);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedProfile, response.getBody());
    }
} 