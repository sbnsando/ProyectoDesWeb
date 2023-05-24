package edu.javeriana.users.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Clase que representa a un usuario en el sistema.
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    /**
     * Constructor vacío de la clase User.
     */
    public User(){ }

    /**
     * Constructor de la clase User.
     *
     * @param name     Nombre del usuario.
     * @param email    Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @param token    Token de acceso del usuario.
     * @param birthday Fecha de nacimiento del usuario.
     * @param id_city  ID de la ciudad del usuario.
     * @param admin    Indica si el usuario es administrador o no.
     * @param active   Indica si el usuario está activo o no.
     */
    public User(String name, String email, String password, String token, Date birthday, int id_city, boolean admin, boolean active){
        this.name = name;
        this.email = email;
        this.password = password;
        this.token = token;
        this.birthday = birthday;
        this.id_city = id_city;
        this.admin = admin;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private  String name;

    @Column(name = "email")
    private  String email;

    @Column(name = "password")
    private String password;

    @Column(name = "token")
    private String token;

    @Column(name = "birthdate")
    private Date birthday;

    @Column(name = "id_city")
    private int id_city;

    @Column(name = "admin")
    private boolean admin;

    @Column(name = "active")
    private boolean active;
}
