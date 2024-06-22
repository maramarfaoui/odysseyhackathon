package com.solarchain.client.services.Contrat;

import com.solarchain.client.entity.Contrat;

import java.util.List;
import java.util.Optional;

public interface IContratService {

    Contrat createContrat(Contrat contrat);
    Optional<Contrat> getContratById(String id);
    Contrat updateContrat(String id, Contrat newContrat);
    void deleteContrat(String id);
    List<Contrat> getAllContrats();
}
