package com.solarchain.offrePs.services.OffrePs;

import com.solarchain.offrePs.entity.OffrePs;

import java.util.List;

public interface IOffreService {
    OffrePs addOffreWithProducts(OffrePs offre) ;
     OffrePs creerOffre(OffrePs offrePs);
     List<OffrePs> getall();
    void supprimerOffrePs(String offrePanneauSolaireId);
}
