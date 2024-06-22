package com.solarchain.offrePs.services.Produit;

import com.solarchain.offrePs.entity.Produit;

import java.util.List;

public interface IProduitService {
    List<Produit> getProduitsByIds(List<String> produitIds) ;
}
