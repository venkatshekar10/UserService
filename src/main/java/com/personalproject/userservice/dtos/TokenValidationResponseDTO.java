package com.personalproject.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenValidationResponseDTO {

    private boolean valid;
    private Long userId;

    public TokenValidationResponseDTO() {}

    public TokenValidationResponseDTO(boolean valid, Long userId) {
        this.valid = valid;
        this.userId = userId;
    }

    public boolean isValid() { return valid; }

}