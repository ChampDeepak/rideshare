package com.deepak.uber.controller;

import com.deepak.uber.Entity.UserEntity;
import com.deepak.uber.dto.UserDto;
import com.deepak.uber.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final UserRepository userRepository;

    @PutMapping("/availability")
    @PreAuthorize("hasAuthority('DRIVER')")
    public ResponseEntity<UserDto> updateAvailability(
            @AuthenticationPrincipal UserEntity driver,
            @RequestBody Boolean isAvailable
    ) {
        driver.setIsAvailable(isAvailable);
        UserEntity updatedDriver = userRepository.save(driver);

        UserDto userDto = new UserDto();
        userDto.setId(updatedDriver.getId());
        userDto.setUsername(updatedDriver.getUsername());
        userDto.setRole(updatedDriver.getRole());

        return ResponseEntity.ok(userDto);
    }
}