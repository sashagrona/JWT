package com.example.jwt.security.jwt;

import com.example.jwt.model.Role;
import com.example.jwt.model.Status;
import com.example.jwt.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class JWTUserFactory {
    public JWTUserFactory() {
    }

    public static JWTUser create(User user) {
        return new JWTUser(user.getId(),
                user.getUsername(), user.getFirstName(),
                user.getLastName(), user.getPassword(), user.getEmail()
                , user.getStatus().equals(Status.ACTIVE), user.getUpdated(), null);

    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles){
        return userRoles.stream().map(role ->
            new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
    }
}
