package com.personalproject.userservice.controller;

import com.personalproject.userservice.dtos.*;
import com.personalproject.userservice.models.Token;
import com.personalproject.userservice.models.User;
import com.personalproject.userservice.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String home() {
        return "Application Working";
    }


    @PostMapping("/register")
    public SignUpResponseDTO signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        User user = userService.signUp(signUpRequestDTO.getName(),
                signUpRequestDTO.getEmailId(),
                signUpRequestDTO.getPassword());

        SignUpResponseDTO signUpResponseDTO = new SignUpResponseDTO();
        return signUpResponseDTO.from(user);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Token token = userService.login(loginRequestDTO.getEmailId(),
                loginRequestDTO.getPassword());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        return loginResponseDTO.from(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequestDTO logoutRequestDTO) {
        Boolean isValidToken = userService.logout(logoutRequestDTO.getTokenValue());
        ResponseEntity<String> responseEntity;
        if(!isValidToken) {
            responseEntity = new ResponseEntity<>("User is unauthorized", HttpStatus.UNAUTHORIZED);
        } else {
            responseEntity = new ResponseEntity<>("Successfully Logged Out", HttpStatus.OK);
        }
        return responseEntity;
    }

    @GetMapping("/validate/{tokenValue}")
    public ResponseEntity<TokenValidationResponseDTO> validateToken(@PathVariable("tokenValue") String tokenValue) {
        User user = userService.validateToken(tokenValue);
        TokenValidationResponseDTO responseDTO;
        ResponseEntity<TokenValidationResponseDTO> responseEntity;

        if (user == null) {
            responseDTO = new TokenValidationResponseDTO(false, null);
            responseEntity = new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
        } else {
            responseDTO = new TokenValidationResponseDTO(true, user.getId());
            responseEntity = new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        return responseEntity;
    }
}
