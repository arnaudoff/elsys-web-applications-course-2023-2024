package org.elsys_bg.ElectronicsRepair.service;

import org.elsys_bg.ElectronicsRepair.controller.resources.ClientResource;
import org.elsys_bg.ElectronicsRepair.entity.Client;

import java.util.List;

public interface ClientService{
    List<ClientResource> findAll();

    ClientResource save(Client client);

    void delete(Client client);

    ClientResource updateClient(Client client);
}