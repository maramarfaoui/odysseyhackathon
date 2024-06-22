package com.solarchain.client.repository;

import com.solarchain.client.entity.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CampaignRepository extends MongoRepository<Campaign, String> {
}
