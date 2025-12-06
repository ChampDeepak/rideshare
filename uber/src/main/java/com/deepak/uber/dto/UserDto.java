package com.deepak.uber.dto;

import com.deepak.uber.Entity.Role;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String username;
    private Role role;
}