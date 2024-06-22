package com.solarchain.client.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Campaign implements Serializable {
    @Id
    private String id;
    private String projectName;

    private String description;
    private Float montant;

    private Float montantCollecte;
    private Integer nbrJour ;
    private Status status ;
    private String imageUrl;


    private String latitude;
    private String longitude;
    private String locationName;


    @DBRef
    private List<ProjetFondRelation> relations;


}
