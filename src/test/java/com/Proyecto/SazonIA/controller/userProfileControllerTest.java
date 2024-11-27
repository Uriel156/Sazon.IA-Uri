package com.Proyecto.SazonIA.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.Proyecto.SazonIA.model.userProfileModel;
import com.Proyecto.SazonIA.service.userProfileService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class userProfileControllerTest {

    @MockBean
    private userProfileService userProfileService;
    
    private userProfileController controller;
    
    @BeforeEach
    void setUp() {
        controller = new userProfileController();
    }
    
    @Test
    void getAllUserProfilesTest() {
        // Arrange
        List<userProfileModel> expectedProfiles = Arrays.asList(new userProfileModel(), new userProfileModel());
        when(userProfileService.getAllUserProfiles()).thenReturn(expectedProfiles);
        
        // Act
        ResponseEntity<List<userProfileModel>> response = controller.getAllUserProfiles();
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedProfiles, response.getBody());
    }
    
    @Test
    void getUserProfileByIdTest() {
        // Arrange
        Integer userId = 1;
        userProfileModel expectedProfile = new userProfileModel();
        when(userProfileService.getUserProfileById(userId)).thenReturn(Optional.of(expectedProfile));
        
        // Act
        ResponseEntity<userProfileModel> response = controller.getUserProfileById(userId);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedProfile, response.getBody());
    }
} 