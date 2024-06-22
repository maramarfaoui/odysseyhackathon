package com.solarchain.client.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solarchain.client.entity.Campaign;
import com.solarchain.client.entity.Status;
import com.solarchain.client.services.Campaign.ICampaignService;
import com.solarchain.client.services.CloudinaryService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/v1/campaign")
public class CampaignController {

    @Autowired
    ICampaignService iCampaignService ;
    CloudinaryService cloudinaryService ;

    //:)
    /*
    @PostMapping("/addProjet/{offreId}")
    public ResponseEntity<Campaign> addProjet(@PathVariable String offreId , @RequestParam("file") MultipartFile file, @RequestParam("projet") String projetDTOString) throws JsonProcessingException {
        Campaign projetDTO = new ObjectMapper().readValue( projetDTOString,  Campaign.class);
        String imageUrl = cloudinaryService.uploadImage(file);
log.info(" project : ",projetDTO.getLocation());
    projetDTO.setImageUrl(imageUrl);
    projetDTO.setStatus(ONGOING);
        iCampaignService.addProjetIfOffreValide(offreId,projetDTO);
        return new ResponseEntity<>(projetDTO, HttpStatus.CREATED);
    }
*/
    @PostMapping("/addProjet/{offreId}")
    public ResponseEntity<Campaign> addProjet(@PathVariable String offreId,
                                              @RequestParam("file") MultipartFile file,
                                              @RequestParam("projet") String projetDTOString) throws JsonProcessingException {
        Campaign projetDTO = new ObjectMapper().readValue(projetDTOString, Campaign.class);
        if (projetDTO.getStatus() == null) {
            projetDTO.setStatus(Status.ONGOING);
        }

        String imageUrl = cloudinaryService.uploadImage(file);
        projetDTO.setImageUrl(imageUrl);
        iCampaignService.addProjetIfOffreValide(offreId, projetDTO);
        log.info("Project location: {}", projetDTO.getLocationName());
        return new ResponseEntity<>(projetDTO, HttpStatus.CREATED);
    }

    //:)
    @GetMapping("/getAllCampaigns")
    public ResponseEntity<List<Campaign>> getAllCampaigns() {
        List<Campaign> projects = iCampaignService.getAllProjets();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
//:)
    @PutMapping("/{id}/assign-fond")
    public  ResponseEntity<?>  assignFondToProjet(@PathVariable("id") String id,
                                     @RequestParam("montant") Float montant) {
        try {
            Campaign projet = iCampaignService.assignFondToProjet(id, montant);
            return ResponseEntity.ok(projet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projet non trouvé.");
        }

    }


    //:)

    @GetMapping("/projetdetail/{id}")
    public Campaign getProjetById(@PathVariable("id") String id) {
        return iCampaignService.getProjetById(id);
    }

    @GetMapping("/projectfunded")
    public ResponseEntity<List<Campaign>> getProjetsWithMontantCollecteZero() {
        List<Campaign> projets = iCampaignService.getProjectsWithZeroFundsCollected();
        return new ResponseEntity<>(projets, HttpStatus.OK);
    }
    @GetMapping("/{id}/pourcentage")
    public ResponseEntity<?> calculatePercentage(@PathVariable("id") String projetId) {
        try {
            float pourcentage = iCampaignService.calculatePercentage(projetId);
            return ResponseEntity.ok(pourcentage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projet non trouvé.");
        }
    }



    @PatchMapping("/update-collecte")
    public Campaign updateMontantCollecte(@RequestBody Map<String, Object> payload) {
        String id = (String) payload.get("id");
        Float montant = ((Number) payload.get("montant")).floatValue();
        return iCampaignService.updateMontantCollecte(id, montant);
    }


    @GetMapping("/export/csv")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=campaigns.csv";
        response.setHeader(headerKey, headerValue);

        List<Campaign> campaigns = iCampaignService.findAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"ID", "Project Name", "Description", "Montant", "Montant Collecte", "Nbr Jour", "Status", "Latitude", "Longitude", "Location Name"};
        String[] nameMapping = {"id", "projectName", "description", "montant", "montantCollecte", "nbrJour", "status", "latitude", "longitude", "locationName"};

        csvWriter.writeHeader(csvHeader);

        for (Campaign campaign : campaigns) {
            csvWriter.write(campaign, nameMapping);
        }

        csvWriter.close();
    }

}
