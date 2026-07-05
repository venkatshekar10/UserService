package com.personalproject.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.personalproject.userservice.models.Roles;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {

    String authority;

    public CustomGrantedAuthority(Roles role) {
        this.authority = role.getRole();
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
