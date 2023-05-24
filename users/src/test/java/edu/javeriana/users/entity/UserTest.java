package edu.javeriana.users.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class UserTest {

    @Test
    public void testConstructorAndGetters() {
        // Prueba del constructor y getters
        String name = "John Doe";
        String email = "john.doe@example.com";
        String password = "password123";
        String token = "token123";
        Date birthday = new Date();
        int id_city = 1;
        boolean admin = true;
        boolean active = true;

        User user = new User(name, email, password, token, birthday, id_city, admin, active);

        Assertions.assertEquals(name, user.getName());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertEquals(token, user.getToken());
        Assertions.assertEquals(birthday, user.getBirthday());
        Assertions.assertEquals(id_city, user.getId_city());
        Assertions.assertEquals(admin, user.isAdmin());
        Assertions.assertEquals(active, user.isActive());
    }

    @Test
    public void testSetters() {
        // Prueba de los setters
        User user = new User();

        String name = "John Doe";
        String email = "john.doe@example.com";
        String password = "password123";
        String token = "token123";
        Date birthday = new Date();
        int id_city = 1;
        boolean admin = true;
        boolean active = true;

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setToken(token);
        user.setBirthday(birthday);
        user.setId_city(id_city);
        user.setAdmin(admin);
        user.setActive(active);

        Assertions.assertEquals(name, user.getName());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertEquals(token, user.getToken());
        Assertions.assertEquals(birthday, user.getBirthday());
        Assertions.assertEquals(id_city, user.getId_city());
        Assertions.assertEquals(admin, user.isAdmin());
        Assertions.assertEquals(active, user.isActive());
    }
}
