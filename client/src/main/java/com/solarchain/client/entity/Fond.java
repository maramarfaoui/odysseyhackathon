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
public class Fond implements Serializable {
    @Id
    private String fondId;
    private Float montant;

    @DBRef
    private List<ProjetFondRelation> relations;
}
