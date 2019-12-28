package com.example.jwt.security;

import com.example.jwt.model.User;
import com.example.jwt.security.jwt.JWTUser;
import com.example.jwt.security.jwt.JWTUserFactory;
import com.example.jwt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JWTUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JWTUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByUsername(s);
        if (user == null){
            throw new UsernameNotFoundException("User: " + s + " not found");
        }
        JWTUser jwtUser = JWTUserFactory.create(user);
        log.info("IN loadByUsername - user with username: {} successfully loaded", s);
        return jwtUser;
    }
}
