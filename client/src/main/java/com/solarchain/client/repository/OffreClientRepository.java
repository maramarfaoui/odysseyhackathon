package com.solarchain.client.repository;

import com.solarchain.client.entity.OffreClient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OffreClientRepository extends MongoRepository<OffreClient, String> {

  // Optional<OffreClient> findById(String offre_id);

    List<OffreClient> findByValideFalse();

}
