package com.deepak.uber.controller;

import com.deepak.uber.Entity.RideEntity;
import com.deepak.uber.Entity.UserEntity;
import com.deepak.uber.dto.RideDto;
import com.deepak.uber.service.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final RideService rideService;
    private final ModelMapper modelMapper;

    @GetMapping("/rides")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<RideDto>> getMyRides(@AuthenticationPrincipal UserEntity user) {
        List<RideEntity> rides = rideService.getAllMyRides(user);
        List<RideDto> rideDtos = rides.stream()
                .map(ride -> modelMapper.map(ride, RideDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(rideDtos);
    }
}