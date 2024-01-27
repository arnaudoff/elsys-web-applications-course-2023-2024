package org.elsys_bg.ElectronicsRepair.mapper;

import org.elsys_bg.ElectronicsRepair.controller.resources.AdminResource;
import org.elsys_bg.ElectronicsRepair.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AdminMapper{
    AdminMapper MAPPER = Mappers.getMapper(AdminMapper.class);

    @Mapping(target = "id", source = "adminResource.id")
    @Mapping(target = "name", source = "adminResource.name")
    @Mapping(target = "password", source = "adminResource.password")
    Admin fromAdminResource(AdminResource adminResource);

    @Mapping(target = "id", source = "admin.id")
    @Mapping(target = "name", source = "admin.name")
    @Mapping(target = "password", source = "admin.password")
    AdminResource toAdminResource(Admin admin);

    List<AdminResource> toAdminResources(List<Admin> admins);
}