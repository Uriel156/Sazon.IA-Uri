package com.Proyecto.SazonIA.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class ConfigurationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer configId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private userProfileModel userProfile;

    @Column(name = "picture_id")
    private String pictureId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "upload_time")
    private java.sql.Timestamp uploadTime;

    public userProfileModel getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(userProfileModel userProfile) {
        this.userProfile = userProfile;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public java.sql.Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(java.sql.Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }


    public void updateProfileDetails(String name, String paternalLastName, String maternalLastName, 
                                   java.sql.Date birthdate, String phoneNumber, String email) {
        this.userProfile.updateProfile(name, paternalLastName, maternalLastName, 
                                     birthdate, phoneNumber, email);
    }

    public void updateProfilePicture(String pictureId, String imageUrl) {
        this.pictureId = pictureId;
        this.imageUrl = imageUrl;
        this.uploadTime = new java.sql.Timestamp(System.currentTimeMillis());
    }
    @Override
    public String toString() {
        return "ConfigurationModel [userProfile=" + userProfile + 
               ", pictureId=" + pictureId + 
               ", imageUrl=" + imageUrl + 
               ", uploadTime=" + uploadTime + "]";
    }
} 