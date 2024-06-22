package com.solarchain.client.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProjetFondRelation implements Serializable {
    @Id
    private String id;

    @DBRef
    private Campaign projet;

    @DBRef
    private Fond fond;


}
