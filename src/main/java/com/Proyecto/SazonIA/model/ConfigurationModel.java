package com.Proyecto.SazonIA.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "configuration")
public class ConfigurationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer configId;

    @NotNull(message = "The user must not be null")
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonBackReference(value = "user-profile")
    @Schema(description = "User to which the configuration belongs")
    private UserProfileModel userProfile;

    @Column(name = "picture_id")
    private String pictureId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "upload_time")
    private Timestamp uploadTime;

    public ConfigurationModel() {
    }

    public ConfigurationModel(UserProfileModel userProfile, String pictureId, String imageUrl, Timestamp uploadTime) {
        this.userProfile = userProfile;
        this.pictureId = pictureId;
        this.imageUrl = imageUrl;
        this.uploadTime = uploadTime;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public UserProfileModel getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfileModel userProfile) {
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

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    public void updateProfilePicture(String pictureId, String imageUrl) {
        this.pictureId = pictureId;
        this.imageUrl = imageUrl;
    }


    @Override
    public String toString() {
        return "ConfigurationModel{" +
                "configId=" + configId +
                ", userProfile=" + userProfile +
                ", pictureId=" + pictureId +
                ", imageUrl=" + imageUrl +
                ", uploadTime=" + uploadTime +
                '}';
    }

    public void updateProfileDetails(String name, String paternalLastName, String maternalLastName, Date birthdate, String phoneNumber, String email) {
        this.userProfile.setName(name);
        this.userProfile.setPaternalLastName(paternalLastName);
        this.userProfile.setMaternalLastName(maternalLastName);
        this.userProfile.setBirthdate(birthdate);
        this.userProfile.setPhoneNumber(phoneNumber);
        this.userProfile.setEmail(email);
    }
}