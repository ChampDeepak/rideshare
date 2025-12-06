package com.deepak.uber.dto;

import com.deepak.uber.Entity.RideStatus;
import lombok.Data;

import java.util.Date;

@Data
public class RideDto {
    private String id;
    private String userId;
    private String driverId;
    private String pickupLocation;
    private String dropLocation;
    private RideStatus status;
    private Date createdAt;
}