package com.personalproject.userservice.repositories;

import com.personalproject.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token save(Token token);

    Optional<Token> findByTokenAndIsDeletedAndExpiryAtGreaterThan(String tokenValue,
                                                                Boolean isDeleted, Date currentDate);
    Optional<Token> findByToken(String tokenValue);
}
