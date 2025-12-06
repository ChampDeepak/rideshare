package com.deepak.uber.controller;

import com.deepak.uber.Entity.RideEntity;
import com.deepak.uber.Entity.UserEntity;
import com.deepak.uber.dto.RideDto;
import com.deepak.uber.dto.RideRequestDto;
import com.deepak.uber.service.RideService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;
    private final ModelMapper modelMapper;

    @PostMapping("/request")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<RideDto> requestRide(
            @Valid @RequestBody RideRequestDto rideRequestDto,
            @AuthenticationPrincipal UserEntity passenger
    ) {
        RideEntity newRide = rideService.requestRide(rideRequestDto, passenger);
        RideDto rideDto = modelMapper.map(newRide, RideDto.class);
        return new ResponseEntity<>(rideDto, HttpStatus.CREATED);
    }

    

    @GetMapping("/requested")
    @PreAuthorize("hasAuthority('DRIVER')")
    public ResponseEntity<List<RideDto>> getAllRequestedRidesDetails()
    {
        List<RideEntity> rides = rideService.getAllRequestedRides();
        List<RideDto> rideDtos = rides.stream()
                .map(ride -> modelMapper.map(ride, RideDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(rideDtos);
    }

    @GetMapping("/{rideId}")
    public ResponseEntity<RideDto> getRideDetails(@PathVariable String rideId) {
        RideEntity ride = rideService.getRideById(rideId);
        RideDto rideDto = modelMapper.map(ride, RideDto.class);
        return ResponseEntity.ok(rideDto);
    }

    @PutMapping("/{rideId}/accept")
    @PreAuthorize("hasAuthority('DRIVER')")
    public ResponseEntity<RideDto> acceptRide(
            @PathVariable String rideId,
            @AuthenticationPrincipal UserEntity driver
    ) {
        RideEntity updatedRide = rideService.acceptRide(rideId, driver);
        RideDto rideDto = modelMapper.map(updatedRide, RideDto.class);
        return ResponseEntity.ok(rideDto);
    }

    @PutMapping("/{rideId}/complete")
    public ResponseEntity<RideDto> completeRide(@PathVariable String rideId) {
        RideEntity updatedRide = rideService.completeRide(rideId);
        RideDto rideDto = modelMapper.map(updatedRide, RideDto.class);
        return ResponseEntity.ok(rideDto);
    }
}