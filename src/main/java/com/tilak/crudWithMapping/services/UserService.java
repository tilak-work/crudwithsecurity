package com.tilak.crudWithMapping.services;

import com.tilak.crudWithMapping.entities.User;
import com.tilak.crudWithMapping.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User registerUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        String refreshToken = jwtService.generateRefreshToken(user.getUsername(), user.getEmail(), user.getPhoneNumber(), user.getRole());
        user.setRefreshToken(refreshToken);
        return userRepository.save(user);
    }

    public String verify(User user) {
        String pass=user.getPassword();
        user=userRepository.findByUsernameOrEmailOrPhoneNumber(user.getUsername());
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),pass));
       if(authentication.isAuthenticated()) {
           String accessToken = jwtService.generateAccessToken(user.getUsername(), user.getEmail(), user.getPhoneNumber(), user.getRole());
           String refreshToken = jwtService.generateRefreshToken(user.getUsername(), user.getEmail(), user.getPhoneNumber(), user.getRole());
           user.setRefreshToken(refreshToken);
           userRepository.save(user);
           return "Access Token: " + accessToken + "\nRefresh Token: " + refreshToken;

       }


       return "FAIL";
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

//    public User processessLogin(User user){
//        if(user.getEmail()==null && user.getPhoneNumber()==null){
//            user=userRepository.findByUsername(user.getUsername());
//        } else if (user.getEmail()==null && user.getUsername()==null) {
//            user=userRepository.findByPhoneNumber(user.getPhoneNumber());
//            System.err.println(user.getPhoneNumber());
//        }else {
//            user=userRepository.findByEmail(user.getEmail());
//        }
//
//
//
//            System.err.println(user);
//            return user;
//
//
//    }

}
