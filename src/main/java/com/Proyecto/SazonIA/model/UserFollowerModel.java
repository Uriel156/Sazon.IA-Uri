package com.Proyecto.SazonIA.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user_follower")
@IdClass(UserFollowerPK.class)
public class UserFollowerModel {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_Id")
    @JsonBackReference(value = "user-follower")
    @NotBlank(message = "The user must not be null")
    @JsonProperty("users")
    private UserProfileModel user_id;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", referencedColumnName = "user_Id")
    @JsonBackReference(value = "user-following")
    @NotNull(message = "The follower user must not be null")
    @JsonProperty("followerUser")
    private UserProfileModel follower_id;

    public UserFollowerModel() {
    }

    public UserFollowerModel(UserProfileModel user_id, UserProfileModel follower_id) {
        this.user_id = user_id;
        this.follower_id = follower_id;
    }

    public UserProfileModel getUser_id() {
        return user_id;
    }

    public void setUser_id(UserProfileModel user_id) {
        this.user_id = user_id;
    }

    public UserProfileModel getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(UserProfileModel follower_id) {
        this.follower_id = follower_id;
    }

    @Override
    public String toString() {
        return "UserFollowerModel{" +
                "user_id=" + user_id +
                ", follower_id=" + follower_id +
                '}';
    }

}
