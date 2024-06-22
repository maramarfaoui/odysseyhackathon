package com.solarchain.client.controller;


import com.solarchain.client.entity.Client;
import com.solarchain.client.services.IClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/v1/clients")
public class ClientController {
    @Autowired
    private IClientService iClientService;

    @GetMapping("/clients/{name}")
    public List<Client> getClientsByName(@PathVariable("name") String name){
      return  iClientService.getClientByName(name) ;
    }

    @PostMapping("addClint")
    public Client add (@RequestBody Client c){
        log.info("addig");
        return  iClientService.addClient(c);
    }
    @DeleteMapping("/delete-client/{client-id}")
    public String deleteReclamation(@PathVariable("client-id") String id) {
        iClientService.delete(id);
        return "client with ID " + id + " has been deleted successfully";
    }
    @PutMapping("/updateClient/{id}")
    public Client update(@RequestBody Client client ,@PathVariable("id") String id) {
        log.info("update");
        return iClientService.update(id,client);
    }

//hedhy taib3a el communication m3a el microservcie tai3 project
    @GetMapping("/project/{projectId}")
    public List<Client> getClientsByProject(@PathVariable("projectId") String projectId){
        return  iClientService.findAllClientsByProjects(projectId) ;
    }
}
