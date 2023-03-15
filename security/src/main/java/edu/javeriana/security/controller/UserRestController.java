package com.example.Projecto.controller;

import com.example.Projecto.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserRestController {

    @PostMapping("/user")
    public User login(@RequestParam("user") String username, @RequestParam("password") String  password){

        String token = getJWTToken(username);
        User user = new User();
        user.setLogin(username);
        user.setToken(token);

        return user;

    }

    private String getJWTToken(String username){

        String secretKey = "mySecretExS";

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String token  = Jwts
                .builder()
                .setId("ferreteriaJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
                )
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 800000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes())
                .compact();

        return "Bearer " + token;
    }
}
