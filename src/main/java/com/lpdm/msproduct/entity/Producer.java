package com.lpdm.msproduct.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Producer {

    private int id;
    private String email;
    private String password;
    private List<AppRole> appRole;
    private String name;
    private String firstName;
    private String tel;
    private LocalDate birthday;
    private LocalDateTime registrationDate;
    private int addressId;
    private Boolean active = true;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<AppRole> getAppRole() {
        return appRole;
    }

    public void setAppRole(List<AppRole> appRole) {
        this.appRole = appRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", appRole=" + appRole +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", tel='" + tel + '\'' +
                ", birthday=" + birthday +
                ", registrationDate=" + registrationDate +
                ", addressId=" + addressId +
                ", active=" + active +
                '}';
    }
}
