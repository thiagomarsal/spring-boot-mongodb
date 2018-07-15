package com.example.repository;

import com.example.domain.Domain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DomainRepository extends MongoRepository<Domain, String> {

    Domain findFirstByDomain(String domain);

    //Supports native JSON query string
    @Query("{domain:'?0'}")
    Domain findCustomByDomain(String domain);

    @Query("{domain: { $regex: ?0 } })")
    List<Domain> findCustomByRegExDomain(String domain);
}
