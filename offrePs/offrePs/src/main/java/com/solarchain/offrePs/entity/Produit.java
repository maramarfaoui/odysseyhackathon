package com.solarchain.offrePs.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Produit {

    @Id
    private String produitId;
    private String nom;
    private float capacite;
    private float prix;
    private String disponibilite;

    @DBRef
    private CategorieProduit categorieProduit;
}
