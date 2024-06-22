package com.solarchain.project.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Project implements Serializable {
    @Id
    private String projectId;
    private String projectName;
    private String description;
    private Integer amount;


}
