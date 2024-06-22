package com.solarchain.client.entity;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ImageModel {
    @Id
    private Long id;
    private String name;
    private String type;
    @Column(length = 50000000)
    private byte[] picByte;

    public ImageModel(String originalFilename, String contentType, byte[] bytes) {
        this.name = originalFilename;
        this.type = contentType;
        this.picByte = bytes;
    }
}
