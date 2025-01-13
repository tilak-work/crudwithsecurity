package com.tilak.crudWithMapping.controllers;

import com.tilak.crudWithMapping.entities.User;
import com.tilak.crudWithMapping.entities.UserPrincipal;
import com.tilak.crudWithMapping.services.JWTService;
import com.tilak.crudWithMapping.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JWTService jwtService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
       return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        System.out.println(user);

        return userService.verify(user);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");


        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String refreshToken = authHeader.substring(7);
            String username = jwtService.extractUsername(refreshToken);

            if (username != null) {

                User user = userService.getUserByUsername(username);

                if (user != null && jwtService.validateToken(refreshToken, new UserPrincipal(user))) {

                    String newAccessToken = jwtService.generateAccessToken(
                            user.getUsername(),
                            user.getEmail(),
                            user.getPhoneNumber(),
                            user.getRole()
                    );

                    Map<String, String> response = new HashMap<>();
                    response.put("accessToken", newAccessToken);
                    return ResponseEntity.ok(response);
                }
            }
        }


        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Refresh Token");
    }



}
