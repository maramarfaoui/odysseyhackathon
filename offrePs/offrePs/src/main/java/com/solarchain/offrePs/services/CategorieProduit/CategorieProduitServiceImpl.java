package com.solarchain.offrePs.services.CategorieProduit;

import com.solarchain.offrePs.entity.CategorieProduit;
import com.solarchain.offrePs.repository.CategorieProduitRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CategorieProduitServiceImpl implements ICategorieProduit{
    @Autowired
    private CategorieProduitRepository categorieProduitRepository;

    public CategorieProduit ajouterCategorieProduit(CategorieProduit categorieProduit) {
        return categorieProduitRepository.save(categorieProduit);
    }

    public List<CategorieProduit> getAllCategorieProduits() {
        return categorieProduitRepository.findAll();
    }

    public Optional<CategorieProduit> getCategorieProduitById(String categorieProduitId) {
        return categorieProduitRepository.findById(categorieProduitId);
    }

    public void supprimerCategorieProduit(String categorieProduitId) {
        categorieProduitRepository.deleteById(categorieProduitId);
    }
}
