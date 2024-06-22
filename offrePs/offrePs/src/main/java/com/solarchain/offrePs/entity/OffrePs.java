package com.solarchain.offrePs.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OffrePs {
    @Id
    private String offrePanneauSolaireId;
    private String titre;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private String plaquePS;
    private String moteurRCT;
    private float prixInstallation;
    private float montant;
    private List<String> produitIds;

    @DBRef
    private List<Produit> produits;

    @DBRef
    private CategorieProduit categorieProduit;
}
