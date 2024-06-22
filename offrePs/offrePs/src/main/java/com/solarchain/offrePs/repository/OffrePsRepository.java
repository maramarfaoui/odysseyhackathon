package com.solarchain.offrePs.repository;

import com.solarchain.offrePs.entity.OffrePs;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OffrePsRepository extends MongoRepository<OffrePs, String> {
}
