package com.Proyecto.SazonIA.dto;

public class UserRequestDTO {

    private String name;
    private String paternalLastName;
    private String maternalLastName;
    private java.sql.Date birthdate;
    private String phoneNumber;
    private String email;
    private String password;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String name, String paternalLastName, String maternalLastName, java.sql.Date birthdate, String phoneNumber, String email, String password) {
        this.name = name;
        this.paternalLastName = paternalLastName;
        this.maternalLastName = maternalLastName;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
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

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "name='" + name + '\'' +
                ", paternalLastName='" + paternalLastName + '\'' +
                ", maternalLastName='" + maternalLastName + '\'' +
                ", birthdate=" + birthdate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
