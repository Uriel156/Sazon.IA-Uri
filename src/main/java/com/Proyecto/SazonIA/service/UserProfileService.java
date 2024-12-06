package com.Proyecto.SazonIA.service;

import com.Proyecto.SazonIA.model.UserFollowerModel;
import com.Proyecto.SazonIA.model.UserProfileModel;
import com.Proyecto.SazonIA.repository.userProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.ArrayList;

@Service
public class UserProfileService {

    @Autowired
    private userProfileRepository userProfileRepository;

    public List<UserProfileModel> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    public UserProfileModel getUserProfileById(Integer id) {
        return userProfileRepository.findUserProfileByUserId(id);
    }

    public UserProfileModel saveUserProfile(UserProfileModel userProfile) {
        return userProfileRepository.save(userProfile);
    }

    public void deleteUserProfile(Integer id) {
        userProfileRepository.deleteById(id);
    }

    public Set<UserFollowerModel> getFollowers(Integer userId) {
        UserProfileModel user = userProfileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        return user.getFollowers();
    }

    public Set<UserFollowerModel> getFollowing(Integer userId) {
        UserProfileModel user = userProfileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        
        return user.getFollowing();
    }

    public void followUser(Integer userId, Integer followerId) {
        UserProfileModel user = userProfileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        UserProfileModel follower = userProfileRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("follower not found"));

        UserFollowerModel userFollower = new UserFollowerModel(user, follower);
        user.addFollowing(userFollower);
        userProfileRepository.save(user);
        userProfileRepository.save(follower);
    }

    public List<UserFollowerModel> getUserFollowers(Integer id) {
        Optional<UserProfileModel> userOptional = userProfileRepository.findById(id);
        return userOptional.map(user -> new ArrayList<>(user.getFollowers())).orElseGet(ArrayList::new);
    }

    public List<UserFollowerModel> getUserFollowing(Integer id) {
        Optional<UserProfileModel> userOptional = userProfileRepository.findById(id);
        return userOptional.map(user -> new ArrayList<>(user.getFollowing())).orElseGet(ArrayList::new);
    }

    public UserProfileModel updateUserProfile(Integer userId, UserProfileModel userProfile) {
        UserProfileModel existingProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        
        existingProfile.setName(userProfile.getName());
        existingProfile.setPaternalLastName(userProfile.getPaternalLastName());
        existingProfile.setMaternalLastName(userProfile.getMaternalLastName());
        existingProfile.setBirthdate(userProfile.getBirthdate());
        existingProfile.setPhoneNumber(userProfile.getPhoneNumber());
        existingProfile.setEmail(userProfile.getEmail());
        
        
        return userProfileRepository.save(existingProfile);
    }

}
