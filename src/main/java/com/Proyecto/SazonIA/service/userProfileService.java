package com.Proyecto.SazonIA.service;

import com.Proyecto.SazonIA.model.userProfileModel;
import com.Proyecto.SazonIA.repository.userProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.ArrayList;

@Service
public class userProfileService {

    @Autowired
    private userProfileRepository userProfileRepository;

    public List<userProfileModel> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    public Optional<userProfileModel> getUserProfileById(Integer id) {
        return userProfileRepository.findById(id);
    }

    public userProfileModel saveUserProfile(userProfileModel userProfile) {
        return userProfileRepository.save(userProfile);
    }

    public void deleteUserProfile(Integer id) {
        userProfileRepository.deleteById(id);
    }

    public Set<userProfileModel> getFollowers(Integer userId) {
        userProfileModel user = userProfileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        
        return user.getFollowers();
    }

    public Set<userProfileModel> getFollowing(Integer userId) {
        userProfileModel user = userProfileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        
        return user.getFollowing();
    }

    public void followUser(Integer userId, Integer followerId) {
        userProfileModel user = userProfileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        userProfileModel follower = userProfileRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("follower not found"));
        
        user.addFollower(follower);
        userProfileRepository.save(user);
        userProfileRepository.save(follower);
    }

    public List<userProfileModel> getUserFollowers(Integer id) {
        Optional<userProfileModel> userOptional = userProfileRepository.findById(id);
        return userOptional.map(user -> new ArrayList<>(user.getFollowers())).orElseGet(ArrayList::new);
    }

    public List<userProfileModel> getUserFollowing(Integer id) {
        Optional<userProfileModel> userOptional = userProfileRepository.findById(id);
        return userOptional.map(user -> new ArrayList<>(user.getFollowing())).orElseGet(ArrayList::new);
    }

    public userProfileModel updateUserProfile(Integer userId, userProfileModel userProfile) {
        userProfileModel existingProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Actualiza los detalles del perfil
        existingProfile.setName(userProfile.getName());
        existingProfile.setPaternalLastName(userProfile.getPaternalLastName());
        existingProfile.setMaternalLastName(userProfile.getMaternalLastName());
        existingProfile.setBirthdate(userProfile.getBirthdate());
        existingProfile.setPhoneNumber(userProfile.getPhoneNumber());
        existingProfile.setEmail(userProfile.getEmail());
        
        // Guarda los cambios en la base de datos
        return userProfileRepository.save(existingProfile);
    }

}
