package com.solarchain.client.services.Contrat;

import com.solarchain.client.entity.Contrat;
import com.solarchain.client.repository.ContratRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ContratServiceImpl implements IContratService {

    @Autowired
    private ContratRepository contratRepository;

    public Contrat createContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }


    public Optional<Contrat> getContratById(String id) {
        return contratRepository.findById(id);
    }


    public Contrat updateContrat(String id, Contrat newContrat) {
        return contratRepository.findById(id)
            .map(contrat -> {
                contrat.setNom(newContrat.getNom());
                contrat.setDescription(newContrat.getDescription());
                contrat.setDateDebut(newContrat.getDateDebut());
                contrat.setDateFin(newContrat.getDateFin());
                contrat.setMontant(newContrat.getMontant());
                contrat.setProjet(newContrat.getProjet());
                contrat.setFond(newContrat.getFond());
                contrat.setTransactionBlockchainId(newContrat.getTransactionBlockchainId());
                return contratRepository.save(contrat);
            })
            .orElse(null);
    }


    public void deleteContrat(String id) {
        contratRepository.deleteById(id);
    }


    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }



}
