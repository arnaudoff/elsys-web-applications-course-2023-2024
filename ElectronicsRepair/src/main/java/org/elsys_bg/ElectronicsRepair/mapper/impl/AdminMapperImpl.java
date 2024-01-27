package org.elsys_bg.ElectronicsRepair.mapper.impl;

import org.elsys_bg.ElectronicsRepair.controller.resources.AdminResource;
import org.elsys_bg.ElectronicsRepair.entity.Admin;
import org.elsys_bg.ElectronicsRepair.mapper.AdminMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class AdminMapperImpl implements AdminMapper{
    @Override
    public Admin fromAdminResource(AdminResource adminResource){
        if(adminResource == null){
            return null;
        }

        Admin admin = new Admin();
        admin.setId(adminResource.getId());
        admin.setName(adminResource.getName());
        admin.setPassword(adminResource.getPassword());

        return admin;
    }

    @Override
    public AdminResource toAdminResource(Admin admin){
        if(admin == null){
            return null;
        }

        AdminResource adminResource = new AdminResource();
        adminResource.setId(admin.getId());
        adminResource.setName(admin.getName());
        adminResource.setPassword(admin.getPassword());

        return adminResource;
    }

    @Override
    public List<AdminResource> toAdminResources(List<Admin> admins){
        if(admins == null){
            return Collections.emptyList();
        }

        List<AdminResource> adminResources = new ArrayList<>();
        for(Admin admin : admins){
            adminResources.add(toAdminResource(admin));
        }

        return adminResources;
    }
}