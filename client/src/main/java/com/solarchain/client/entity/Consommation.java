package com.solarchain.client.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "consommations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Consommation implements Serializable {
    @Id
    private String id;
    private Date date_debut;
    private Date date_fin;
    private Double electricityConsumption;
    private String offreClientId;}
