package com.tilak.crudWithMapping.services;

import com.tilak.crudWithMapping.entities.User;
import com.tilak.crudWithMapping.entities.UserPrincipal;
import com.tilak.crudWithMapping.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(identifier);


        if (user != null) {
            return new UserPrincipal(user);
        }
        throw new UsernameNotFoundException("User not found with username : "+identifier);
    }
}
