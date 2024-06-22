package com.solarchain.client.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OffreClient implements Serializable {
    @Id
    private String id;
    private Double superficie;
    private Boolean valide;
    private List<String> imageUrls;
    private String consommationId;
    private String userId;
    private String latitude;
    private String longitude;
}
