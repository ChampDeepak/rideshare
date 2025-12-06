package com.deepak.uber.service;

import com.deepak.uber.Entity.RideEntity;
import com.deepak.uber.Entity.RideStatus;
import com.deepak.uber.Entity.UserEntity;
import com.deepak.uber.dto.RideRequestDto;
import com.deepak.uber.repository.RideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RideService {

    private final RideRepository rideRepository;

    public RideEntity requestRide(RideRequestDto rideRequestDto, UserEntity passenger) {
        RideEntity ride = new RideEntity();
        ride.setUserId(passenger.getId());
        ride.setPickupLocation(rideRequestDto.getPickupLocation());
        ride.setDropLocation(rideRequestDto.getDropLocation());
        ride.setStatus(RideStatus.REQUESTED);
        ride.setCreatedAt(new Date());
        return rideRepository.save(ride);
    }

    public RideEntity getRideById(String rideId) {
        return rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found with id: " + rideId));
    }

    public List<RideEntity> getAllMyRides(UserEntity user) {
        List<RideEntity> rides = rideRepository.findByUserId(user.getId());
        rides.sort(Comparator.comparing(RideEntity::getStatus));
        return rides;
    }

    public List<RideEntity> getAllRequestedRides() {
        return rideRepository.findByStatus(RideStatus.REQUESTED);
    }

    public List<RideEntity> getAllRides() {
        return rideRepository.findAll();
    }

    public RideEntity acceptRide(String rideId, UserEntity driver) {
        RideEntity ride = getRideById(rideId);
        if (ride.getStatus() != RideStatus.REQUESTED) {
            throw new RuntimeException("Ride is not in REQUESTED state");
        }
        ride.setDriverId(driver.getId());
        ride.setStatus(RideStatus.ACCEPTED);
        return rideRepository.save(ride);
    }

    public RideEntity completeRide(String rideId) {
        RideEntity ride = getRideById(rideId);
        if (ride.getStatus() != RideStatus.ACCEPTED) {
            throw new RuntimeException("Ride is not in ACCEPTED state");
        }
        ride.setStatus(RideStatus.COMPLETED);
        return rideRepository.save(ride);
    }
}