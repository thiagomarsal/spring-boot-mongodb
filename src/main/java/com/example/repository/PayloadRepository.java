package com.example.repository;

import com.example.domain.Payload;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PayloadRepository extends MongoRepository<Payload, String> {

//    @Query("{iteration : {$gte : 0}, maxRetry: {$lt: 5}, processed: {$eq: false}}")
//    @Query("{ $expr: { $lte: [ \"$iteration\" , \"$maxRetry\" ] } }")
    List<Payload> findAllIterationLessThanMaxRetry();
}
