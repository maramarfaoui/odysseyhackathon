package com.solarchain.client.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solarchain.client.entity.OffreClient;
import com.solarchain.client.services.CloudinaryService;
import com.solarchain.client.services.OffreClient.IOffreService;
import com.solarchain.client.services.OffreClient.OffreServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")

@RequestMapping("/api/v1/offres")
public class OffreClientController {

    IOffreService iOffreService ;
    CloudinaryService cloudinaryService ;
 OffreServiceImpl offreService ;

    @PostMapping("/add")
    public ResponseEntity<OffreClient> addOffreClient(
        @RequestParam("files") List<MultipartFile> files,
        @RequestParam("offre") String offreClientDTOString
    ) throws JsonProcessingException {
        OffreClient offreClientDTO = new ObjectMapper().readValue(offreClientDTOString, OffreClient.class);
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            String imageUrl = cloudinaryService.uploadImage(file);
            imageUrls.add(imageUrl);
        }
        offreClientDTO.setImageUrls(imageUrls);
        offreClientDTO.setValide(false);
        OffreClient newOffre = iOffreService.addOffre(offreClientDTO);
        return new ResponseEntity<>(newOffre, HttpStatus.CREATED);
    }



    @PutMapping("/{id}/approve")
    public ResponseEntity<Void> approveOffreClient(@PathVariable String id) {
        OffreClient offreClient = iOffreService.findById(id);
        if (offreClient != null) {
            iOffreService.approveOffre(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAllOffreClient")
    public List<OffreClient> get()
    {
      return  iOffreService.allOffre();
    }


    @GetMapping("/nonValides")
    public ResponseEntity<List<OffreClient>> getOffresNonValides() {
        List<OffreClient> offres = iOffreService.getOffresNonValides();
        return ResponseEntity.ok(offres);
    }


//    @GetMapping("/get2")
//    public List<OffreClient> get2()
//    {
//        return  offreService.getAllOffres();
//    }

//    @GetMapping("/nonVal")
//    public ResponseEntity<List<OffreClient>> getOffresNonValides2() {
//        List<OffreClient> offres = offreService.getNonValidatedOffres();
//        return ResponseEntity.ok(offres);
//    }

    @PutMapping("/{id}/update-consommation-id")
    public ResponseEntity<Void> updateConsommationId(@PathVariable String id, @RequestBody String consommationId) {
        offreService.updateConsommationId(id, consommationId);
        return ResponseEntity.ok().build();
    }
}
