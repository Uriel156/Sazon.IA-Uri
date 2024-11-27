package com.Proyecto.SazonIA.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.Proyecto.SazonIA.model.ConfigurationModel;
import com.Proyecto.SazonIA.model.userProfileModel;
import com.Proyecto.SazonIA.repository.ConfigurationRepository;

import java.util.Optional;

@SpringBootTest
public class ConfigurationServiceTest {

    @MockBean
    private ConfigurationRepository configurationRepository;
    
    private ConfigurationService configurationService;
    
    @BeforeEach
    void setUp() {
        configurationService = new ConfigurationService();
    }
    
    @Test
    void updateUserProfileDetailsTest() {
        // Arrange
        Integer userId = 1;
        userProfileModel userProfile = new userProfileModel();
        ConfigurationModel config = new ConfigurationModel();
        when(configurationRepository.findByUserProfile_UserId(userId)).thenReturn(Optional.of(config));
        when(configurationRepository.save(config)).thenReturn(config);
        
        // Act
        ConfigurationModel result = configurationService.updateUserProfileDetails(userId, userProfile);
        
        // Assert
        assertNotNull(result);
        verify(configurationRepository).save(config);
    }
    
    @Test
    void updateProfilePictureTest() {
        // Arrange
        Integer userId = 1;
        String pictureId = "123";
        String imageUrl = "http://example.com/image.jpg";
        ConfigurationModel config = new ConfigurationModel();
        when(configurationRepository.findByUserProfile_UserId(userId)).thenReturn(Optional.of(config));
        when(configurationRepository.save(config)).thenReturn(config);
        
        // Act
        ConfigurationModel result = configurationService.updateProfilePicture(userId, pictureId, imageUrl);
        
        // Assert
        assertNotNull(result);
        verify(configurationRepository).save(config);
    }
} 