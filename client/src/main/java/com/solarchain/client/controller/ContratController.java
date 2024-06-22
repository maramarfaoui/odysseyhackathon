package com.solarchain.client.controller;

import com.solarchain.client.entity.Contrat;
import com.solarchain.client.services.Contrat.IContratService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")

@RequestMapping("/api/v1/contratq")
public class ContratController {

    IContratService contratService ;

    @PostMapping("addcontrat")
    public ResponseEntity<Contrat> createContrat(@RequestBody Contrat contrat) {
        Contrat createdContrat = contratService.createContrat(contrat);
        return new ResponseEntity<>(createdContrat, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Contrat> getContratById(@PathVariable String id) {
        Optional<Contrat> contrat = contratService.getContratById(id);
        return contrat.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrat> updateContrat(@PathVariable String id, @RequestBody Contrat contrat) {
        Contrat updatedContrat = contratService.updateContrat(id, contrat);
        return updatedContrat != null ?
            new ResponseEntity<>(updatedContrat, HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrat(@PathVariable String id) {
        contratService.deleteContrat(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Contrat>> getAllContrats() {
        List<Contrat> contrats = contratService.getAllContrats();
        return new ResponseEntity<>(contrats, HttpStatus.OK);
    }

}
