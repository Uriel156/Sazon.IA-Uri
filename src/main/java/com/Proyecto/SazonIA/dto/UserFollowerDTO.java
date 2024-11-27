package com.Proyecto.SazonIA.dto;

public class UserFollowerDTO {

    private Integer userId;
    private String name;
    private String paternalLastName;
    private String maternalLastName;
    private String email;

    public UserFollowerDTO() {
    }

    public UserFollowerDTO(Integer userId, String name, String paternalLastName, String maternalLastName, String email) {
        this.userId = userId;
        this.name = name;
        this.paternalLastName = paternalLastName;
        this.maternalLastName = maternalLastName;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserFollowerDTO{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", paternalLastName='" + paternalLastName + '\'' +
                ", maternalLastName='" + maternalLastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
