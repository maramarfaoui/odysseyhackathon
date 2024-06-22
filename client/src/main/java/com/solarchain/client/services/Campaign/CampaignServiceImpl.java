package com.solarchain.client.services.Campaign;

import com.solarchain.client.entity.Campaign;
import com.solarchain.client.entity.OffreClient;
import com.solarchain.client.repository.CampaignRepository;
import com.solarchain.client.repository.OffreClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
@Slf4j
public class CampaignServiceImpl implements ICampaignService {
    @Autowired
    OffreClientRepository offreClientRepository;
    @Autowired
    CampaignRepository campaignRepository;

    private final Web3j web3j;
    private final String contractAddress = "0xf8e81D47203A594245E36C48e151709F0C19fBe8";

    @Autowired
    public CampaignServiceImpl(Web3j web3j) {
        this.web3j = web3j;
    }

    public void interactWithContract() {
      /*  MySmartContract contract = MySmartContract.load(
            contractAddress,
            web3j,
            new ClientTransactionManager(web3j, contractAddress),
            BigInteger.valueOf(0), // gas price
            BigInteger.valueOf(3000000)); // gas limit

        // Call contract function
        try {
            TransactionReceipt receipt = contract.someFunction().send();
            System.out.println("Transaction complete: " + receipt.getTransactionHash());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }







    public List<Campaign> findAll() {
        return campaignRepository.findAll();
    }






    @Override
    public List<Campaign> getAllProjets() {
        return campaignRepository.findAll();

    }

    public boolean addProjetIfOffreValide(String offreId, Campaign projet) {
        OffreClient offre = offreClientRepository.findById(offreId).orElse(null);
        if (offre != null && offre.getValide()) {
            campaignRepository.save(projet);
            return true;
        }
        return false;
    }

    @Override
    public Campaign getProjetById(String projetId) {
        return campaignRepository.findById(projetId).orElse(null);
    }

    @Override
    public Campaign updateMontantCollecte(String id, Float montant) {
        Optional<Campaign> optionalProjet = campaignRepository.findById(id);
        if (optionalProjet.isPresent()) {
            Campaign projet = optionalProjet.get();
            projet.setMontantCollecte(projet.getMontantCollecte() + montant);
            return campaignRepository.save(projet);
        } else {
            throw new RuntimeException("Project not found");
        }
    }
    @Override
    public Campaign assignFondToProjet(String projetId, Float montant) {

        Campaign projet = campaignRepository.findById(projetId).orElse(null);

        if (projet != null) {
            if (projet.getNbrJour() == 0) {
                throw new IllegalArgumentException("Le projet est déjà terminé. Vous ne pouvez pas donner de fonds.");
            }
            Float montantTotalProjet = projet.getMontant();
            Float montantCollecte = projet.getMontantCollecte();

            if (montant <= montantTotalProjet) {
                montantCollecte += montant;
                montantTotalProjet -= montant;
                projet.setMontant(montantTotalProjet);
                projet.setMontantCollecte(montantCollecte);
                campaignRepository.save(projet);
            } else {
                throw new IllegalArgumentException("Le montant des fonds donnés dépasse le montant total du projet.");
            }
        } else {
            throw new IllegalArgumentException("Projet non trouvé.");
        }

        return projet;
    }

    @Override
    public float calculatePercentage(String projetId) {
        Campaign projet = campaignRepository.findById(projetId).orElse(null);
        if (projet != null) {
            Float montantCollecte = projet.getMontantCollecte();
            if (projet.getMontant() != 0) {
                return (montantCollecte / projet.getMontant()) * 100;
            } else {
                throw new IllegalArgumentException("Le montant total du projet ne peut pas être zéro.");
            }
        } else {
            throw new IllegalArgumentException("Projet non trouvé.");
        }
    }

    @Override
    public List<Campaign> getProjectsWithZeroFundsCollected() {
        List<Campaign> allProjects = campaignRepository.findAll();
        List<Campaign> fullyFundedProjects = new ArrayList<>();

        for (Campaign projet : allProjects) {
            if (projet.getMontantCollecte().equals(projet.getMontant())) {
                fullyFundedProjects.add(projet);
            }
        }

        return fullyFundedProjects;
    }
}
