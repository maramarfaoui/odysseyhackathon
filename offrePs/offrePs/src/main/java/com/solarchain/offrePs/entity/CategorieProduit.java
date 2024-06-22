package com.solarchain.offrePs.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CategorieProduit {
    @Id
    private String id;
    private String code;
    private String libelle;
}
