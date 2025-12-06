package com.deepak.uber.dto;


import com.deepak.uber.Entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDto
{
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be least 8 characters long")
    private String password;

    @NotNull(message = "Role must be provided")
    private Role role;
}
