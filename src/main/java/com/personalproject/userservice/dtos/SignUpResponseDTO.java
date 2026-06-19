package com.personalproject.userservice.dtos;

import com.personalproject.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDTO {
    private String name;
    private String emailId;
    private String password;

    public SignUpResponseDTO from(User user) {
        SignUpResponseDTO signUpResponseDTO = new SignUpResponseDTO();
        signUpResponseDTO.setName(user.getName());
        signUpResponseDTO.setEmailId(user.getEmailId());
        signUpResponseDTO.setPassword(user.getPassword());
        return signUpResponseDTO;
    }
}
