package com.Proyecto.SazonIA.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import java.util.Set;
import java.sql.Date;
import java.util.HashSet;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;

@Entity
public class userProfileModel {
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

    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private ConfigurationModel configuration;

    @ManyToMany
    @JoinTable(
        name = "user_followers",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private Set<userProfileModel> followers = new HashSet<>();

    @ManyToMany(mappedBy = "followers")
    private Set<userProfileModel> following = new HashSet<>();

    @Column(name = "profile_picture")
    private String profilePicture;

    private String id;

    public userProfileModel(String id) {
        this.id = id;
    }

    public userProfileModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public java.sql.Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(java.sql.Date birthdate) {
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

    public Set<userProfileModel> getFollowers() {
        return followers;
    }

    public Set<userProfileModel> getFollowing() {
        return following;
    }

    public void addFollower(userProfileModel follower) {
        this.followers.add(follower);
        follower.following.add(this);
    }

    public void removeFollower(userProfileModel follower) {
        this.followers.remove(follower);
        follower.following.remove(this);
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", paternalLastName=" + paternalLastName
                + ", maternalLastName=" + maternalLastName + ", birthdate=" + birthdate + ", phoneNumber=" + phoneNumber
                + ", email=" + email + ", password=" + password + "]";
    }
   public void updateProfile(String name, String paternalLastName, String maternalLastName, Date birthdate,
            String phoneNumber, String email) {
        this.name = name;
        this.paternalLastName = paternalLastName;
        this.maternalLastName = maternalLastName;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
