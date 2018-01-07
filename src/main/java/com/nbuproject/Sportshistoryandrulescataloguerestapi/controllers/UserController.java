package com.nbuproject.Sportshistoryandrulescataloguerestapi.controllers;


import com.nbuproject.Sportshistoryandrulescataloguerestapi.entities.User;
import com.nbuproject.Sportshistoryandrulescataloguerestapi.entities.UserRole;
import com.nbuproject.Sportshistoryandrulescataloguerestapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        user.setRole(UserRole.USER);
        String cryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(cryptedPassword);
        this.userRepository.save(user);
        user = this.userRepository.findUserByName(user.getUsername());
        setSecurityHolder(user);
        response.put("id", user.getId());
        response.put("name", user.getName());
        return ResponseEntity.ok(response);
    }

    private void setSecurityHolder(@RequestBody User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        User checkedUser = this.userRepository.findUserByUsername(user.getUsername());
        boolean isPasswordMatching = BCrypt.checkpw(user.getPassword(), checkedUser.getPassword());
        if (isPasswordMatching) {
            setSecurityHolder(checkedUser);
            response.put("id", checkedUser.getId());
            response.put("name", checkedUser.getName());
            return ResponseEntity.ok(response);
        }
        response.put("authorization","failed");
        return ResponseEntity.badRequest().body(response);
    }
}
