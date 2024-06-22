package com.solarchain.client.services.OffreClient;

import com.solarchain.client.entity.*;

import java.util.List;

public interface IOffreService {

    OffreClient addOffre(OffreClient c);
    void approveOffre(String offreId);
    OffreClient findById(String id);
    List<OffreClient> allOffre();
    public List<OffreClient> getOffresNonValides();
}
