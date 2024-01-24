package org.elsys_bg.ElectronicsRepair.service;

import org.elsys_bg.ElectronicsRepair.controller.resources.ClientContactResource;
import org.elsys_bg.ElectronicsRepair.entity.ClientContact;

import java.util.List;

public interface ClientContactService{
    List<ClientContactResource> findAll();

    ClientContactResource save(ClientContact clientContact);

    void delete(ClientContact contact);

    ClientContactResource updateClientContact(ClientContact contact);
}