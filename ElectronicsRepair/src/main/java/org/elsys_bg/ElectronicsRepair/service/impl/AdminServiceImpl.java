package org.elsys_bg.ElectronicsRepair.service.impl;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.AdminResource;
import org.elsys_bg.ElectronicsRepair.entity.Admin;
import org.elsys_bg.ElectronicsRepair.mapper.AdminMapper;
import org.elsys_bg.ElectronicsRepair.repository.AdminRepository;
import org.elsys_bg.ElectronicsRepair.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    public final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    public Admin getById(Long adminId){
        return adminRepository.getById(adminId);
    }

    @Override
    public List<AdminResource> findAll(){
        return adminMapper.toAdminResources(adminRepository.findAll());
    }

    @Override
    public AdminResource save(Admin admin){
        return adminMapper.toAdminResource(adminRepository.save(admin));
    }

    @Override
    public void delete(Admin admin){
        adminRepository.delete(admin);
    }

    @Override
    public AdminResource updateAdmin(Admin admin) throws NoSuchElementException {
        Admin existingAdmin = adminRepository.findById(Long.valueOf(admin.getId())).orElse(null);

        if(existingAdmin != null){
            existingAdmin.setName(admin.getName());
            existingAdmin.setPassword(admin.getPassword());
            adminRepository.save(existingAdmin);
        }else{
            throw new NoSuchElementException("ERR: Admin with ID " + admin.getId() + " does not exist.");
        }

        return adminMapper.toAdminResource(existingAdmin);
    }

    public void deleteAdminByName(String username){
        adminRepository.deleteByUsername(username);
    }

    public boolean adminExists(String username){
        return adminRepository.adminExists(username);
    }

    public boolean checkAdminPassword(String username, String password){
        return adminRepository.checkAdminPassword(username, password);
    }

    public AdminResource signUp(String username, String password){
        AdminResource newAdmin = new AdminResource();
        newAdmin.setName(username);
        newAdmin.setPassword(password);
        return adminMapper.toAdminResource(adminRepository.save(adminMapper.fromAdminResource(newAdmin)));
    }
}