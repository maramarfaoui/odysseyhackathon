package com.solarchain.client.services.Consommation;

import com.solarchain.client.entity.Consommation;
import com.solarchain.client.repository.ConsommationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ConsommationServiceImpl implements IConsommationService {
    @Autowired
    private final ConsommationRepository consommationRepository;

    public Consommation ajouterconsommmation(Consommation consommation) {
        Consommation savedConsommation = consommationRepository.save(consommation);
        log.info("Consommation saved with ID: " + savedConsommation.getId());
        return savedConsommation;
    }

    public List<Consommation> ajouterConsommations(List<Consommation> consommations) {
        List<Consommation> savedConsommations = consommationRepository.saveAll(consommations);
        savedConsommations.forEach(consommation -> log.info("Consommation saved with ID: " + consommation.getId()));
        return savedConsommations;
    }
    public Consommation findById(String id) {
        return consommationRepository.findById(id).orElse(null);
    }
}
