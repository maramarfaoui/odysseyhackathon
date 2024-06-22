package com.solarchain.client.repository;

import com.solarchain.client.entity.Client;
import com.solarchain.client.entity.Fond;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FondRepository extends MongoRepository<Fond, String> {
}
