package com.solarchain.offrePs.services.Produit;

import com.solarchain.offrePs.entity.Produit;
import com.solarchain.offrePs.repository.ProduitRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProduitServiceImpl implements IProduitService{

    @Autowired
    private ProduitRepository produitRepository;
    public Produit ajouterProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Optional<Produit> getProduitById(String produitId) {
        return produitRepository.findById(produitId);
    }

    public void supprimerProduit(String produitId) {
        produitRepository.deleteById(produitId);
    }
    public List<Produit> getProduitsByIds(List<String> produitIds) {
        // Vérifiez que la liste n'est pas nulle et ne contient pas de valeurs nulles
        if (produitIds == null || produitIds.contains(null)) {
            throw new IllegalArgumentException("The given Ids of entities must not be null");
        }
        return produitRepository.findAllById(produitIds);
    }
}
