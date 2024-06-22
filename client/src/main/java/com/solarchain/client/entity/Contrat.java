package com.solarchain.client.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Contrat implements Serializable {
    @Id
    private String contratId;
    private String nom;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private Float montant;


    @DBRef
    private Campaign projet;

    @DBRef
    private Fond fond;

    private String transactionBlockchainId;
}
