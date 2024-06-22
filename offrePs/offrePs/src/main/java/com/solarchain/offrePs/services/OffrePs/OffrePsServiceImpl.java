package com.solarchain.offrePs.services.OffrePs;

import com.solarchain.offrePs.entity.OffrePs;
import com.solarchain.offrePs.entity.Produit;
import com.solarchain.offrePs.repository.OffrePsRepository;
import com.solarchain.offrePs.repository.ProduitRepository;
import com.solarchain.offrePs.services.Produit.ProduitServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OffrePsServiceImpl implements IOffreService{

    @Autowired
    ProduitRepository produitRepository;

    private OffrePsRepository offrePsRepository;

    private ProduitServiceImpl produitService;

    public OffrePs creerOffre(OffrePs offrePs) {
        List<String> produitIds = offrePs.getProduitIds();
        if (produitIds == null || produitIds.contains(null)) {
            throw new IllegalArgumentException("The given Ids of entities must not be null");
        }

        List<Produit> produits = produitService.getProduitsByIds(produitIds);
        offrePs.setProduits(produits);

        return offrePsRepository.save(offrePs);
    }

    public OffrePs addOffreWithProducts(OffrePs offre) {
//        List<Produit> nouveauxProduits = new ArrayList<>();
//        for (Produit produit : produits) {
//            Optional<Produit> existingProduit = produitRepository.findByNom(produit.getNom());
//            if (existingProduit.isPresent()) {
//                nouveauxProduits.add(existingProduit.get());
//            } else {
//                nouveauxProduits.add(produitRepository.save(produit));
//            }
//        }
//
//        offre.setProduits(nouveauxProduits);
        return offrePsRepository.save(offre);
    }

    public  List<OffrePs> getall(){
        return offrePsRepository.findAll();
}
    public OffrePs proposerMeilleurPanneauSolaire(double consommationClient, String localisation) {
        // Récupérer toutes les offres disponibles
      //  List<OffrePs> offresDisponibles = offreService.getAllOffres();

        // Filtrer les offres en fonction de la consommation du client
        //List<OffrePs> offresFiltrees = filtrerOffres(offresDisponibles, consommationClient);

        // Calculer le meilleur rapport qualité-prix parmi les offres filtrées
     //   OffrePs meilleureOffre = calculerMeilleurRapportQualitePrix(offresFiltrees);

        // Prendre en compte la localisation si nécessaire
        // Vous pouvez intégrer des données sur l'ensoleillement régional, l'orientation du toit, etc.

       // return meilleureOffre;
        return null;
    }

    private List<OffrePs> filtrerOffres(List<OffrePs> offres, double consommationClient) {
        // Implémentez la logique pour filtrer les offres en fonction de la consommation du client
        // Par exemple, gardez uniquement les offres dont la capacité est supérieure à la consommation du client
        return offres.stream()
            //.filter(offre -> offre.getCapacite() >= consommationClient)
            .collect(Collectors.toList());
    }

    private OffrePs calculerMeilleurRapportQualitePrix(List<OffrePs> offres) {
        // Implémentez la logique pour calculer le meilleur rapport qualité-prix
        // Par exemple, sélectionnez l'offre avec le prix le plus bas par unité de capacité
        //return offres.stream()
           // .min(Comparator.comparingDouble(offre -> offre.getPrixInstallation() / offre.getCapacite()))
           // .orElse(null);
        return null;
    }
    public void supprimerOffrePs(String offrePanneauSolaireId) {
        offrePsRepository.deleteById(offrePanneauSolaireId);
    }
}
