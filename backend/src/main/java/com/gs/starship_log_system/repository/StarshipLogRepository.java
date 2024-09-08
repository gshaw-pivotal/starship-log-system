package com.gs.starship_log_system.repository;

import com.gs.starship_log_system.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface StarshipLogRepository extends MongoRepository<Log, UUID> {

    public List<Log> findByUserId(UUID userId);

    public Log findByIdAndUserId(UUID id, UUID userId);
}
