package com.solarchain.client.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) {
        try {
            // Convertir le fichier MultipartFile en un tableau de bytes
            byte[] bytes = file.getBytes();

            // Utiliser Cloudinary pour télécharger l'image
            Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.emptyMap());

            // Récupérer l'URL de l'image téléchargée depuis Cloudinary
            return (String) uploadResult.get("url");
        } catch (IOException e) {
            // Gérer les erreurs en cas de problème lors du téléchargement de l'image
            e.printStackTrace();
            return null;
        }
    }
}

