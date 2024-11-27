package com.Proyecto.SazonIA.controller;

import com.Proyecto.SazonIA.dto.UserFollowerDTO;
import com.Proyecto.SazonIA.dto.UserRequestDTO;
import com.Proyecto.SazonIA.model.UserFollowerModel;
import com.Proyecto.SazonIA.model.UserProfileModel;
import com.Proyecto.SazonIA.service.UserProfileService;

import io.swagger.v3.oas.annotations.Operation;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@Tag(name = "View followers", description = "Here you can see the followers and followed")
@RequestMapping("/userprofile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/followers/{userId}")
    public ResponseEntity<Set<UserFollowerDTO>> getFollowersByUserId(@PathVariable Integer userId) {
        UserProfileModel user = userProfileService.getUserProfileById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Set<UserFollowerModel> followers = user.getFollowers();
        Set<UserFollowerDTO> followersDTO = new HashSet<>();
        for (UserFollowerModel follower : followers) {
            followersDTO.add(new UserFollowerDTO(
                    follower.getFollower_id().getUserId(),
                    follower.getFollower_id().getName(),
                    follower.getFollower_id().getPaternalLastName(),
                    follower.getFollower_id().getMaternalLastName(),
                    follower.getFollower_id().getEmail()
            ));
        }
        return ResponseEntity.ok(followersDTO);
    }

    @GetMapping("/following/{userId}")
    public ResponseEntity<Set<UserFollowerDTO>> getFollowingByUserId(@PathVariable Integer userId) {
        UserProfileModel user = userProfileService.getUserProfileById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Set<UserFollowerModel> following = user.getFollowing();
        Set<UserFollowerDTO> followingDTO = new HashSet<>();
        for (UserFollowerModel follower : following) {
            followingDTO.add(new UserFollowerDTO(
                    follower.getUser_id().getUserId(),
                    follower.getUser_id().getName(),
                    follower.getUser_id().getPaternalLastName(),
                    follower.getUser_id().getMaternalLastName(),
                    follower.getUser_id().getEmail()
            ));
        }
        return ResponseEntity.ok(followingDTO);
    }

    public ResponseEntity<List<UserProfileModel>> getAllUserProfiles() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllUserProfiles'");
    }

    public ResponseEntity<UserProfileModel> getUserProfileById(Integer userId) {

        throw new UnsupportedOperationException("Unimplemented method 'getUserProfileById'");
    }

    @PutMapping("/update/{userId}")
    @Operation(
            summary = "Update user profile",
            description = "Update the user profile",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User profile to update",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserRequestDTO.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Update",
                                    value = "{\n"
                                            + "  \"name\": \"Carlos\",\n"
                                            + "  \"paternalLastName\": \"Gonzalez\",\n"
                                            + "  \"maternalLastName\": \"Perez\",\n"
                                            + "  \"birthdate\": \"1999-12-31\",\n"
                                            + "  \"phoneNumber\": \"1234567890\",\n"
                                            + "  \"email\": \"fred@example.com\",\n"
                                            + "  \"password\": \"password\"\n"
                                            + "}"
                            )
                    )
            )
    )

    public ResponseEntity<UserProfileModel> updateUserProfile(
            @PathVariable Integer userId,
            @RequestBody UserRequestDTO userProfile) {
        try {
            UserProfileModel user = userProfileService.getUserProfileById(userId);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            user.setName(userProfile.getName());
            user.setPaternalLastName(userProfile.getPaternalLastName());
            user.setMaternalLastName(userProfile.getMaternalLastName());
            user.setBirthdate(userProfile.getBirthdate());
            user.setPhoneNumber(userProfile.getPhoneNumber());
            user.setEmail(userProfile.getEmail());
            user.setPassword(userProfile.getPassword());
            user.setUserId(userId);
            UserProfileModel updatedProfile = userProfileService.updateUserProfile(userId, user);
            return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
        } catch (
                RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
