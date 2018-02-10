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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(path = "/setAdmin" , method = RequestMethod.POST)
    public ResponseEntity setAdmin(@RequestBody String userId) {
        User user = userRepository.findUserById(userId);
        if(user.getRole().equals(UserRole.ADMIN)){
            user.setRole(UserRole.USER);
        }else {
            user.setRole(UserRole.ADMIN);
        }
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/disableUser" , method = RequestMethod.POST)
    public ResponseEntity disableUser(@RequestBody String userId) {
        User user = userRepository.findUserById(userId);
        if(user.isDisabled()) {
            user.setDisabled(false);
        }else {
            user.setDisabled(true);
        }
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public ResponseEntity<Collection<User>> getAll() {
        Collection<User> users = userRepository.findAll();
        return  ResponseEntity.ok(users);
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        user.setRole(UserRole.USER);
        String cryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(cryptedPassword);
        User checkUser = this.userRepository.findUserByUsername(user.getUsername());
        if(checkUser != null) {
            response.put("exists", "true");
            return ResponseEntity.badRequest().body(response);
        }
        this.userRepository.save(user);
        user = this.userRepository.findUserByUsername(user.getUsername());
        setSecurityHolder(user);
        response.put("id", user.getId());
        response.put("name", user.getName());
        response.put("userName", user.getUsername());
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
        if(checkedUser == null ) {
            return ResponseEntity.badRequest().body(response);
        }
        boolean isPasswordMatching = BCrypt.checkpw(user.getPassword(), checkedUser.getPassword());
        if (isPasswordMatching) {
            setSecurityHolder(checkedUser);
            response.put("id", checkedUser.getId());
            response.put("name", checkedUser.getName());
            response.put("userName", checkedUser.getUsername());
            response.put("admin", String.valueOf(checkedUser.getRole()));
            if(checkedUser.isDisabled()) {
                response.put("disabled", "true");
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(response);
        }
        response.put("authorization","failed");
        return ResponseEntity.badRequest().body(response);
    }

    @RequestMapping(path = "/changeData", method = RequestMethod.POST)
    public ResponseEntity changeUserData(@RequestBody User changeUserData) {
        User user = userRepository.findUserById(changeUserData.getId());
        if(changeUserData.getName() != null) {
            user.setName(changeUserData.getName());
        }
        if(changeUserData.getEmail() != null) {
            user.setEmail(changeUserData.getEmail());
        }
        if(changeUserData.getPassword() != null) {
            String cryptedPassword = BCrypt.hashpw(changeUserData.getPassword(), BCrypt.gensalt());
            user.setPassword(cryptedPassword);
        }
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

}
