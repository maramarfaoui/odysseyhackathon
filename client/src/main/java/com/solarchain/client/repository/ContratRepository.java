package com.solarchain.client.repository;

import com.solarchain.client.entity.Client;
import com.solarchain.client.entity.Contrat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContratRepository extends MongoRepository<Contrat, String> {
}
