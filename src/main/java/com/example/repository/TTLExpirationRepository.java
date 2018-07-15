package com.example.repository;

import com.example.domain.TTLExpiration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TTLExpirationRepository extends MongoRepository<TTLExpiration, String> {

    TTLExpiration findFirstByValue(String value);
}
