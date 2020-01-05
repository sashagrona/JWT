package com.example.jwt.rest;

import com.example.jwt.dto.UserDTO;
import com.example.jwt.model.User;
import com.example.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);
        if (user == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        UserDTO userDTO = UserDTO.fromUser(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
