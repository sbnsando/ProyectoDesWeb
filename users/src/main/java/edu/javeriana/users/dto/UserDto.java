package edu.javeriana.users.dto;

import jakarta.persistence.Column;

import java.util.Date;

public class UserDto {

    private int identityNumb;

    private String name;

    private Date birthday;

    private int idCity;

    private boolean admin;


    private boolean active;

    public UserDto(){
    }

    public UserDto(int identityNumb, String name, Date birthday, int idCity, boolean admin, boolean active) {
        this.identityNumb = identityNumb;
        this.name = name;
        this.birthday = birthday;
        this.idCity = idCity;
        this.admin = admin;
        this.active = active;
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
