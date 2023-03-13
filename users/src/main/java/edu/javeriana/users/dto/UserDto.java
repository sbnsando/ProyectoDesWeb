package edu.javeriana.users.dto;

import jakarta.persistence.Column;

import java.util.Date;

public class UserDto {

    private int identityNumb;

    private String name;

    private Date birthday;

    private int idCity;

    private boolean admin;

    public UserDto(){
    }

    public int getIdentityNumb() {
        return identityNumb;
    }

    public void setIdentityNumb(int identityNumb) {
        this.identityNumb = identityNumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
