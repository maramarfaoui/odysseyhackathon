package com.solarchain.client.repository;

import com.solarchain.client.entity.Client;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClientRepository extends MongoRepository<Client, String> {

    List<Client> findByName(String name);
    List<Client>  findAllByProjectId(String projectId);
}
