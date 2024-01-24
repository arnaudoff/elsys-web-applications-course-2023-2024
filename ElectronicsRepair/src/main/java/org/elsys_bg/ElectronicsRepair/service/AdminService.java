package org.elsys_bg.ElectronicsRepair.service;

import org.elsys_bg.ElectronicsRepair.controller.resources.AdminResource;
import org.elsys_bg.ElectronicsRepair.entity.Admin;

import java.util.List;

public interface AdminService{
    List<AdminResource> findAll();

    AdminResource save(Admin admin);

    void delete(Admin admin);

    AdminResource updateAdmin(Admin admin);
}