package com.personalproject.userservice.service;

import com.personalproject.userservice.models.Token;
import com.personalproject.userservice.models.User;

public interface UserService {
    User signUp(String name, String emailId, String password);
    Token login(String emailId, String password);
    Boolean logout(String tokenValue);
    User validateToken(String tokenValue);
}
