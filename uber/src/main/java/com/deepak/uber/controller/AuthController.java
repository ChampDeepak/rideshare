package com.deepak.uber.controller;

import com.deepak.uber.Entity.UserEntity;
import com.deepak.uber.dto.AuthResponseDto;
import com.deepak.uber.dto.LoginRequestDto;
import com.deepak.uber.dto.UserDto;
import com.deepak.uber.dto.RegisterRequestDto;
import com.deepak.uber.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController
{
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegisterRequestDto registerRequestDto)
    {
        UserEntity registeredUser = authService.register(registerRequestDto);
        UserDto userDto = new UserDto();
        userDto.setId(registeredUser.getId());
        userDto.setUsername(registeredUser.getUsername());
        userDto.setRole(registeredUser.getRole());
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto)
    {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }
}
