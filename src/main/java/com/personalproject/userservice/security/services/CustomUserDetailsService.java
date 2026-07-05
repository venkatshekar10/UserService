package com.personalproject.userservice.security.services;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.personalproject.userservice.models.User;
import com.personalproject.userservice.repositories.UserRepository;
import com.personalproject.userservice.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmailId(username);

        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found");
        }

        User user = optionalUser.get();

        return new CustomUserDetails(user);
    }
}
