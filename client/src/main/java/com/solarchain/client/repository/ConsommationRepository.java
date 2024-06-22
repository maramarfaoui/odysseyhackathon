package com.solarchain.client.repository;

import com.solarchain.client.entity.Consommation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ConsommationRepository extends MongoRepository<Consommation, String> {
    List<Consommation> findByOffreClientId(String offreClientId);

}
