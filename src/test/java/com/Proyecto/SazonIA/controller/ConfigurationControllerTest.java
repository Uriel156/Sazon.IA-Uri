package com.Proyecto.SazonIA.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.Proyecto.SazonIA.model.ConfigurationModel;
import com.Proyecto.SazonIA.model.userProfileModel;
import com.Proyecto.SazonIA.service.ConfigurationService;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class ConfigurationControllerTest {
    
    @MockBean
    private ConfigurationService configurationService;
    
    private configurationController configurationController;
    
    @BeforeEach
    void setUp() {
        configurationController = new configurationController();
    }
    
    @Test
    void updateUserProfileDetailsTest() {
        // Arrange
        Integer userId = 1;
        userProfileModel userProfile = new userProfileModel();
        ConfigurationModel expectedConfig = new ConfigurationModel();
        when(configurationService.updateUserProfileDetails(userId, userProfile)).thenReturn(expectedConfig);
        
        // Act
        ResponseEntity<ConfigurationModel> response = configurationController.updateUserProfileDetails(userId, userProfile);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedConfig, response.getBody());
    }
    
    @Test
    void updateProfilePictureTest() {
        // Arrange
        Integer userId = 1;
        Map<String, String> pictureInfo = new HashMap<>();
        pictureInfo.put("pictureId", "123");
        pictureInfo.put("imageUrl", "http://example.com/image.jpg");
        ConfigurationModel expectedConfig = new ConfigurationModel();
        when(configurationService.updateProfilePicture(userId, "123", "http://example.com/image.jpg"))
            .thenReturn(expectedConfig);
            
        // Act
        ResponseEntity<ConfigurationModel> response = configurationController.updateProfilePicture(userId, pictureInfo);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedConfig, response.getBody());
    }
} 