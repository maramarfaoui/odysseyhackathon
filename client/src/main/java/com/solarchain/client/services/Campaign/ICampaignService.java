package com.solarchain.client.services.Campaign;

import com.solarchain.client.entity.Campaign;

import java.util.List;

public interface ICampaignService {

    public List<Campaign> getAllProjets() ;

    boolean addProjetIfOffreValide(String offreId, Campaign projet);
    Campaign getProjetById(String projetId);
    Campaign assignFondToProjet(String projetId, Float montant);
    float calculatePercentage(String projetId);
     List<Campaign> getProjectsWithZeroFundsCollected();
    Campaign updateMontantCollecte(String id, Float montant);
    List<Campaign> findAll();
}
