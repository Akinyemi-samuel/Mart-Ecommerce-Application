package com.samfrosh.service;

import com.samfrosh.model.User;
import com.samfrosh.exception.UserExits;
import com.samfrosh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsSeriviceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
       Optional<User> optional = userRepository.findByEmail(username);
       User user = null;
       if (optional.isPresent()) {
           user = optional.get();
       }else{
           try {
               throw new UserExits("Username was not found");
           } catch (UserExits e) {
               throw new RuntimeException(e);
           }
       }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}

