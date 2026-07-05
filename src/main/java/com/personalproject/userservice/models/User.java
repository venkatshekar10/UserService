package com.personalproject.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String name;
    private String emailId;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Roles> roles;

    /*
    User : Roles
    1 -> M
    M -> 1
    M : M
     */
}
