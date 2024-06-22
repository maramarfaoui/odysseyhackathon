package com.solarchain.offrePs.controller;

import com.solarchain.offrePs.entity.Produit;
import com.solarchain.offrePs.services.Produit.ProduitServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1/produitPs")
public class ProduitController {
    @Autowired
    private ProduitServiceImpl produitService;

    @PostMapping("/add")
    public ResponseEntity<Produit> ajouterProduit(@RequestBody Produit produit) {
        Produit nouveauProduit = produitService.ajouterProduit(produit);
        return new ResponseEntity<>(nouveauProduit, HttpStatus.CREATED);
    }

    @GetMapping("getprod")
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitService.getAllProduits();
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable String id) {
        return produitService.getProduitById(id)
            .map(produit -> new ResponseEntity<>(produit, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerProduit(@PathVariable String id) {
        produitService.supprimerProduit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
