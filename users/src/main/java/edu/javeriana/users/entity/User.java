package edu.javeriana.users.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    public User(int identityNumb, String name, Date birthday, int idCity, boolean admin, boolean active) {
        this.identityNumb = identityNumb;
        this.name = name;
        this.birthday = birthday;
        this.idCity = idCity;
        this.admin = admin;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "identityNumb")
    private int identityNumb;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "idCity")
    private int idCity;

    @Column(name = "admin")
    private boolean admin;

    @Column(name = "active")
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
