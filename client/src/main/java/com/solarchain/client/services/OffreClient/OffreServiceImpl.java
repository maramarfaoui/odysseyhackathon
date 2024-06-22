package com.solarchain.client.services.OffreClient;

import com.solarchain.client.entity.OffreClient;
import com.solarchain.client.repository.ConsommationRepository;
import com.solarchain.client.repository.OffreClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OffreServiceImpl implements IOffreService {

    @Autowired
    OffreClientRepository offreClientRepository ;
ConsommationRepository consommationRepository ;
    @Override
    public OffreClient addOffre(OffreClient c) {
        log.info("inserting start");



        return offreClientRepository.save(c);



    }

    @Override
    public OffreClient findById(String id) {
        return offreClientRepository.findById(id).orElse(null);
    }

    @Override
    public void approveOffre(String offreId) {
        OffreClient offreClient = findById(offreId);
        if (offreClient != null) {
            offreClient.setValide(true);
            offreClientRepository.save(offreClient);
        }
    }
    @Override
    public List<OffreClient> allOffre(){
        return offreClientRepository.findAll();

    }
//    public List<OffreClient> getAllOffres() {
//        List<OffreClient> offres = offreClientRepository.findAll();
//        for (OffreClient offre : offres) {
//            List<Consommation> consommations = consommationRepository.findByOffreClientId(offre.getId());
//            offre.setConsommations(consommations);
//        }
//        return offres;
//    }
    @Override
    public List<OffreClient> getOffresNonValides() {
        return offreClientRepository.findByValideFalse();
    }

//    public List<OffreClient> getNonValidatedOffres() {
//        List<OffreClient> offres = offreClientRepository.findByValideFalse();
//        for (OffreClient offre : offres) {
//            List<Consommation> consommations = consommationRepository.findByOffreClientId(offre.getId());
//            offre.setConsommations(consommations);
//        }
//        return offres;
//    }
public void updateConsommationId(String offreId, String consommationId) {
    OffreClient offreClient = offreClientRepository.findById(offreId).orElse(null);
    if (offreClient != null) {
        offreClient.setConsommationId(consommationId);
        offreClientRepository.save(offreClient);
    }
}

}
