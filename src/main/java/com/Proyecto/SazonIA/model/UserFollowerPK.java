package com.Proyecto.SazonIA.model;

import java.io.Serializable;

public class UserFollowerPK implements Serializable {

    private UserProfileModel user_id;
    private UserProfileModel follower_id;

    public UserFollowerPK() {
    }

    public UserFollowerPK(UserProfileModel user_id, UserProfileModel follower_id) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserFollowerPK userFollowerPK)) return false;
        return user_id.equals(userFollowerPK.user_id) && follower_id.equals(userFollowerPK.follower_id);
    }

    @Override
    public int hashCode() {
        return user_id.hashCode() + follower_id.hashCode();
    }

    @Override
    public String toString() {
        return "UserFollowerPK{" +
                "user_id=" + user_id +
                ", follower_id=" + follower_id +
                '}';
    }
}
