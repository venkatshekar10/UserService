package com.personalproject.userservice.dtos;

import com.personalproject.userservice.models.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private String tokenValue;

    public LoginResponseDTO from(Token token) {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setTokenValue(token.getToken());
        return loginResponseDTO;
    }
}
