package com.personalproject.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Token extends BaseModel {
    private String token;
    private Date expiryAt;

    @ManyToOne
    User user;

    /*
    Token : User
    1 : 1
    M : 1
    M : 1
     */
}
