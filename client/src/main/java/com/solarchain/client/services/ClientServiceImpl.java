package com.solarchain.client.services;


import com.solarchain.client.entity.Client;
import com.solarchain.client.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ClientServiceImpl implements IClientService{
    @Autowired
    ClientRepository clientRepository ;


    @Override
    public List<Client> getClientByName(String name) {
        log.info("Getting Clients from the repo", name);
        List<Client> clients = clientRepository.findByName(name);

        if(clients.isEmpty()) {
            log.info("No Client");
            return new ArrayList<Client>();
        }

        log.info("this is the list of clients",clients.size(),name);
        return clients ;



    }

    @Override
    public Client addClient(Client c) {
        log.info("inserting start");
       Client client =  clientRepository.insert(c);
       log.info("success" + client);
        return client;

    }

    @Override
    public void delete(String id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client update(String id, Client updatedClient) {
        Optional<Client> existingClientOptional = clientRepository.findById(id);

        if (existingClientOptional.isPresent()) {
            Client existingClient = existingClientOptional.get();

            if (updatedClient.getName() != null) {
                existingClient.setName(updatedClient.getName());
            }

            if (updatedClient.getPrenom() != null) {
                existingClient.setPrenom(updatedClient.getPrenom());
            }

            if (updatedClient.getProjectId() != null) {
                existingClient.setProjectId(updatedClient.getProjectId());
            }

            return clientRepository.save(existingClient);
        } else {
            return null;
        }
    }

    @Override
    public List<Client> findAllClientsByProjects(String projectId) {
        return clientRepository.findAllByProjectId(projectId);
    }
}
