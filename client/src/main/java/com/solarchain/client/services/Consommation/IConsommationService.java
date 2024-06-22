package com.solarchain.client.services.Consommation;

import com.solarchain.client.entity.Consommation;

import java.util.List;

public interface IConsommationService {
    Consommation ajouterconsommmation(Consommation consommation);
    List<Consommation> ajouterConsommations(List<Consommation> consommations) ;
}
