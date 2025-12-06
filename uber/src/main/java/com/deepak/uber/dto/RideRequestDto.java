package com.deepak.uber.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RideRequestDto {
    @NotBlank
    private String pickupLocation;

    @NotBlank
    private String dropLocation;
}