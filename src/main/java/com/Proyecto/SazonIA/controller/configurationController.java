package com.Proyecto.SazonIA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.Proyecto.SazonIA.model.ConfigurationModel;
import com.Proyecto.SazonIA.model.userProfileModel;
import com.Proyecto.SazonIA.service.ConfigurationService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;

@RestController
@Tag(name = "Configuration profile", description = "here the profile and profile photo are updated")
@RequestMapping("/configuration")
public class configurationController {
    @Autowired
    private ConfigurationService configurationService;

    @PutMapping("/{userId}/update")
    public ResponseEntity<ConfigurationModel> updateUserProfileDetails(
            @PathVariable Integer userId, 
            @RequestBody userProfileModel userProfile) {
        try {
            // Se asegura de que el perfil se actualice correctamente
            ConfigurationModel updatedConfig = configurationService.updateUserProfileDetails(userId, userProfile);
            return new ResponseEntity<>(updatedConfig, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/update-picture")
    public ResponseEntity<ConfigurationModel> updateProfilePicture(
            @PathVariable Integer userId, 
            @RequestBody Map<String, String> pictureInfo) {
        try {
            String pictureId = pictureInfo.get("pictureId");
            String imageUrl = pictureInfo.get("imageUrl");
            ConfigurationModel updatedConfig = configurationService.updateProfilePicture(userId, pictureId, imageUrl);
            return new ResponseEntity<>(updatedConfig, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
} 