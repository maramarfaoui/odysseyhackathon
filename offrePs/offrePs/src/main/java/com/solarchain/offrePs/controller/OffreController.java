package com.solarchain.offrePs.controller;

import com.solarchain.offrePs.entity.*;
import com.solarchain.offrePs.services.OffrePs.IOffreService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1/offres")
public class OffreController {

    @Autowired
    IOffreService offreService ;

    @PostMapping("/add")
    public OffrePs addOffreWithProducts(@RequestBody OffrePs offre) {
        return offreService.addOffreWithProducts(offre);
    }

    @PostMapping("/create")
    public ResponseEntity<OffrePs> createOffre(@RequestBody OffrePs offrePs) {
        if (offrePs.getProduitIds() == null || offrePs.getProduitIds().contains(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        OffrePs nouvelleOffre = offreService.creerOffre(offrePs);
        return new ResponseEntity<>(nouvelleOffre, HttpStatus.CREATED);
    }

    @GetMapping("/getOffrePs")
    public ResponseEntity<List<OffrePs>> getAllOffrePs() {
        List<OffrePs> offrePs = offreService.getall();
        return new ResponseEntity<>(offrePs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerOffrePs(@PathVariable String id) {
        offreService.supprimerOffrePs(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
