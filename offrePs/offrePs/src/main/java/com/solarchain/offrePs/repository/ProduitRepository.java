package com.solarchain.offrePs.repository;

import com.solarchain.offrePs.entity.Produit;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProduitRepository extends MongoRepository<Produit, String> {
    List<Produit> findAllById(Iterable<String> ids);

}
