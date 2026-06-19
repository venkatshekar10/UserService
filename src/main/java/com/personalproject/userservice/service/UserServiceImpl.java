package com.personalproject.userservice.service;

import com.personalproject.userservice.models.Token;
import com.personalproject.userservice.models.User;
import com.personalproject.userservice.repositories.TokenRepository;
import com.personalproject.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    TokenRepository tokenRepository;

    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public User signUp(String name, String emailId, String password) {
        User user = new User();
        user.setName(name);
        user.setEmailId(emailId);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }

    @Override
    public Token login(String emailId, String password) {
        Optional<User> optionalUser = userRepository.findByEmailId(emailId);
        if(optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        Token token = new Token();
        String tokenValue = RandomStringUtils.randomAlphanumeric(128);
        token.setToken(tokenValue);
        token.setUser(user);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);
        Date date = calendar.getTime();

        token.setExpiryAt(date);
        token.setIsDeleted(false);

        return tokenRepository.save(token);
    }

    @Override
    public Boolean logout(String tokenValue) {
        Optional<Token> optionalToken = tokenRepository.findByToken(tokenValue);
        if(optionalToken.isEmpty()) {
            return false;
        }
        Token token = optionalToken.get();
        token.setIsDeleted(true);
        tokenRepository.save(token);
        return true;
    }

    @Override
    public User validateToken(String tokenValue) {
        /*
        1.Token Exist in DB
        2.isDeleted should be false
        3.expiryDate should be futureDate
         */

        Optional<Token> optionalToken = tokenRepository.findByTokenAndIsDeletedAndExpiryAtGreaterThan(tokenValue,
                false, new Date());
        if(optionalToken.isEmpty()) {
            return null;
        }
        Token token= optionalToken.get();
        return token.getUser();
    }
}
