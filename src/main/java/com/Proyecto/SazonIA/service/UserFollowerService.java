package com.Proyecto.SazonIA.service;

import com.Proyecto.SazonIA.model.UserFollowerModel;
import com.Proyecto.SazonIA.model.UserProfileModel;
import com.Proyecto.SazonIA.repository.UserFollowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFollowerService {

    private UserFollowerRepository userFollowerRepository;

    private UserProfileService userProfileService;

    @Autowired
    public UserFollowerService(UserFollowerRepository userFollowerRepository, UserProfileService userProfileService) {
        this.userFollowerRepository = userFollowerRepository;
        this.userProfileService = userProfileService;
    }

    public UserFollowerService() {
    }

    public List<UserFollowerModel> findFollowersByUserId(Integer userId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return userFollowerRepository.findFollowersByUserId(userId, pageable);
    }

    public List<UserFollowerModel> findFollowingsByFollowerId(Integer followerId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return userFollowerRepository.findFollowingsByFollowerId(followerId, pageable);
    }
}
