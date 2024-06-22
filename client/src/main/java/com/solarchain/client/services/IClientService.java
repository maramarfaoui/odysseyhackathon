package com.solarchain.client.services;

import com.solarchain.client.ClientApplication;
import com.solarchain.client.entity.Client;

import java.util.List;

public interface IClientService {

    List<Client> getClientByName(String name);
    Client addClient(Client c);
    void delete(String id);
    Client update(String id,Client client);

    List<Client> findAllClientsByProjects(String projectId);
}
