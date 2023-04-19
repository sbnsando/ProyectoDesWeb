package edu.javeriana.users.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter

@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    public User(String name, String email, String password, String login, String token, Date birthdate, String id_city, boolean admin, boolean active) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.login = login;
        this.token = token;
        this.birthdate = birthdate;
        this.id_city = id_city;
        this.admin = admin;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "login")
    private String login;

    @Column(name = "token")
    private String token;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "id_city")
    private String id_city;

    @Column(name = "admin")
    private boolean admin;

    @Column(name = "active")
    private boolean active;

}
