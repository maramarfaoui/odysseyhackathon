package com.solarchain.client.controller;

import com.solarchain.client.entity.Consommation;
import com.solarchain.client.services.Consommation.ConsommationServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/v1/consommations")
public class ConsommationController {

    @Autowired
    private final ConsommationServiceImpl consommationService;

    @PostMapping("/add")
    public ResponseEntity<?> ajouterConsommation(@RequestBody Consommation consommation) {
        try {
            Consommation savedConsommation = consommationService.ajouterconsommmation(consommation);
            log.info("Consommation returned with ID: " + savedConsommation.getId());
            return new ResponseEntity<>(savedConsommation, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("An error occurred while adding consommation", e);
            return new ResponseEntity<>("An error occurred while adding consommation", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add-multiple")
    public ResponseEntity<?> ajouterConsommations(@RequestBody List<Consommation> consommations) {
        try {
            List<Consommation> savedConsommations = consommationService.ajouterConsommations(consommations);
            savedConsommations.forEach(consommation -> log.info("Consommation returned with ID: " + consommation.getId()));
            return new ResponseEntity<>(savedConsommations, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("An error occurred while adding consommations", e);
            return new ResponseEntity<>("An error occurred while adding consommations", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/cons/{id}")
    public Consommation getConsommationById(@PathVariable("id") String id) {
        return consommationService.findById(id);
    }
}
