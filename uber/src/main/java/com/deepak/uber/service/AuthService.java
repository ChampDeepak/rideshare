package com.deepak.uber.service;

import com.deepak.uber.Entity.UserEntity;
import com.deepak.uber.dto.AuthResponseDto;
import com.deepak.uber.dto.LoginRequestDto;
import com.deepak.uber.dto.RegisterRequestDto;
import com.deepak.uber.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public UserEntity register(RegisterRequestDto registerRequest)
    {
        if(userRepository.findByUsername(registerRequest.getUsername()).isPresent())
        {
            throw new RuntimeException("Username already in use: " + registerRequest.getUsername());
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setRole(registerRequest.getRole());

        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        return userRepository.save(newUser);
    }

    public AuthResponseDto login(LoginRequestDto loginRequestDto)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );

        var user = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken(user);

        return new AuthResponseDto(jwtToken);
    }
}
