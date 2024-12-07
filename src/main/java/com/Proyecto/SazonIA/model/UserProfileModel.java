package com.Proyecto.SazonIA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.Set;
import java.sql.Date;
import java.util.HashSet;

@Entity
@Table(name = "users")
public class UserProfileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "first_name", nullable = false)
    private String name;

    @Column(name = "last_name_father", nullable = false)
    private String paternalLastName;

    @Column(name = "last_name_mother", nullable = false)
    private String maternalLastName;

    @Column(name = "birth_date", nullable = false)
    private java.sql.Date birthdate;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;


    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-profile")
    @JsonProperty("configuration")
    private ConfigurationModel configuration;

    @JsonManagedReference(value = "user-follower")
    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Followers of the user")
    @JsonIgnore
    private Set<UserFollowerModel> followers = new HashSet<>();

    @JsonManagedReference(value = "user-following")
    @OneToMany(mappedBy = "follower_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Users followed by the user")
    @JsonIgnore
    private Set<UserFollowerModel> following = new HashSet<>();

    public UserProfileModel() {
    }

    public UserProfileModel(String name, String paternalLastName, String maternalLastName, Date birthdate, String phoneNumber, String email, String password) {
        this.name = name;
        this.paternalLastName = paternalLastName;
        this.maternalLastName = maternalLastName;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaternalLastName() {
        return paternalLastName;
    }

    public void setPaternalLastName(String paternalLastName) {
        this.paternalLastName = paternalLastName;
    }

    public String getMaternalLastName() {
        return maternalLastName;
    }

    public void setMaternalLastName(String maternalLastName) {
        this.maternalLastName = maternalLastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ConfigurationModel getConfiguration() {
        return configuration;
    }

    public void setConfiguration(ConfigurationModel configuration) {
        this.configuration = configuration;
    }

    public Set<UserFollowerModel> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<UserFollowerModel> followers) {
        this.followers = followers;
    }

    public Set<UserFollowerModel> getFollowing() {
        return following;
    }

    public void setFollowing(Set<UserFollowerModel> following) {
        this.following = following;
    }

    public void addFollower(UserFollowerModel follower) {
        followers.add(follower);
    }

    public void addFollowing(UserFollowerModel following) {
        this.following.add(following);
    }

    public void removeFollower(UserFollowerModel follower) {
        followers.remove(follower);
    }

    public void removeFollowing(UserFollowerModel following) {
        this.following.remove(following);
    }
}
