package com.solarchain.project.entity;



import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullProjectResponse {
    private String projectName ;
    private String description ;
    private Integer amount ;
    List<Client> clients ;
}
