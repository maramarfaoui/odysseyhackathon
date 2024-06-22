package com.solarchain.offrePs.repository;

import com.solarchain.offrePs.entity.CategorieProduit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategorieProduitRepository extends MongoRepository<CategorieProduit, String> {
}
