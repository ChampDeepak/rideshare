package com.deepak.uber.repository;

import com.deepak.uber.Entity.RideEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.deepak.uber.Entity.RideStatus;
import java.util.List;

@Repository
public interface RideRepository extends MongoRepository<RideEntity, String> {
    List<RideEntity> findByStatus(RideStatus status);
    List<RideEntity> findByUserId(String userId);
}