package com.solarchain.offrePs.controller;

import com.solarchain.offrePs.entity.CategorieProduit;
import com.solarchain.offrePs.services.CategorieProduit.CategorieProduitServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1/categorie")
public class CategorieProduitController {
    @Autowired
    private CategorieProduitServiceImpl categorieProduitService;

    @PostMapping("/add")
    public ResponseEntity<CategorieProduit> ajouterCategorieProduit(@RequestBody CategorieProduit categorieProduit) {
        CategorieProduit nouvelleCategorieProduit = categorieProduitService.ajouterCategorieProduit(categorieProduit);
        return new ResponseEntity<>(nouvelleCategorieProduit, HttpStatus.CREATED);
    }

    @GetMapping("/getcat")
    public ResponseEntity<List<CategorieProduit>> getAllCategorieProduits() {
        List<CategorieProduit> categories = categorieProduitService.getAllCategorieProduits();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieProduit> getCategorieProduitById(@PathVariable String id) {
        return categorieProduitService.getCategorieProduitById(id)
            .map(categorieProduit -> new ResponseEntity<>(categorieProduit, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerCategorieProduit(@PathVariable String id) {
        categorieProduitService.supprimerCategorieProduit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
