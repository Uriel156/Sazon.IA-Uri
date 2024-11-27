package com.Proyecto.SazonIA.controller;

import com.Proyecto.SazonIA.model.userProfileModel;
import com.Proyecto.SazonIA.service.userProfileService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@Tag(name = "View followers", description = "Here you can see the followers and followed")
@RequestMapping("/userprofile")
public class userProfileController {

    @Autowired
    private userProfileService userProfileService;

    @GetMapping("/{userId}/followers")
    public ResponseEntity<Set<userProfileModel>> getFollowersByUserId(@PathVariable Integer userId) {
        Optional<userProfileModel> user = userProfileService.getUserProfileById(userId);
        return user.map(u -> ResponseEntity.ok(u.getFollowers()))
                   .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<Set<userProfileModel>> getFollowingByUserId(@PathVariable Integer userId) {
        Optional<userProfileModel> user = userProfileService.getUserProfileById(userId);
        return user.map(u -> ResponseEntity.ok(u.getFollowing()))
                   .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<userProfileModel>> getAllUserProfiles() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllUserProfiles'");
    }

	public ResponseEntity<userProfileModel> getUserProfileById(Integer userId) {
	
		throw new UnsupportedOperationException("Unimplemented method 'getUserProfileById'");
	}

    @PutMapping("/{userId}/update")
    public ResponseEntity<userProfileModel> updateUserProfile(
            @PathVariable Integer userId, 
            @RequestBody userProfileModel userProfile) {
        try {
            userProfileModel updatedProfile = userProfileService.updateUserProfile(userId, userProfile);
            return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
