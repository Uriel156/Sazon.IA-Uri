package com.Proyecto.SazonIA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Proyecto.SazonIA.model.ConfigurationModel;
import com.Proyecto.SazonIA.model.UserProfileModel;
import com.Proyecto.SazonIA.repository.ConfigurationRepository;

@Service
public class ConfigurationService {
    @Autowired
    private ConfigurationRepository configurationRepository;
    
    public ConfigurationModel updateUserProfileDetails(Integer userId, UserProfileModel userProfile) {
        // Busca la configuraci�n del usuario por su ID
        ConfigurationModel config = configurationRepository.findByUserProfile_UserId(userId)
            .orElseThrow(() -> new RuntimeException("Configuration not found"));
        
        // Actualiza los detalles del perfil
        config.updateProfileDetails(userProfile.getName(), userProfile.getPaternalLastName(),
                                  userProfile.getMaternalLastName(), userProfile.getBirthdate(),
                                  userProfile.getPhoneNumber(), userProfile.getEmail());
        
        // Actualiza la contrase�a si se proporciona
        if (userProfile.getPassword() != null) {
            config.getUserProfile().setPassword(userProfile.getPassword());
        }
        
        // Guarda los cambios en la base de datos
        return configurationRepository.save(config);
    }

    public ConfigurationModel updateProfilePicture(Integer userId, String pictureId, String imageUrl) {
        ConfigurationModel config = configurationRepository.findByUserProfile_UserId(userId)
            .orElseThrow(() -> new RuntimeException("Configuration not found"));
            
        config.updateProfilePicture(pictureId, imageUrl);
        return configurationRepository.save(config);
    }
} 