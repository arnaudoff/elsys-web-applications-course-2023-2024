package org.elsys_bg.ElectronicsRepair.service.impl;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.SupportedDeviceForRepairResource;
import org.elsys_bg.ElectronicsRepair.controller.resources.WorkerResource;
import org.elsys_bg.ElectronicsRepair.entity.DeviceType;
import org.elsys_bg.ElectronicsRepair.entity.SupportedDeviceForRepair;
import org.elsys_bg.ElectronicsRepair.mapper.DeviceTypeMapper;
import org.elsys_bg.ElectronicsRepair.mapper.SupportedDeviceForRepairMapper;
import org.elsys_bg.ElectronicsRepair.mapper.impl.SupportedDeviceForRepairMapperImpl;
import org.elsys_bg.ElectronicsRepair.miscellaneous.SupportedDevicesProjection;
import org.elsys_bg.ElectronicsRepair.repository.SupportedDeviceForRepairRepository;
import org.elsys_bg.ElectronicsRepair.service.SupportedDeviceForRepairService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SupportedDeviceForRepairServiceImpl implements SupportedDeviceForRepairService{
    public final SupportedDeviceForRepairRepository supportedDeviceForRepairRepository;
    public final SupportedDeviceForRepairMapper supportedDeviceForRepairMapper;
    public final DeviceTypeMapper deviceTypeMapper;

    public SupportedDeviceForRepairResource getById(Long supportedDeviceForRepairId){
        return supportedDeviceForRepairMapper.toSupportedDeviceForRepairResource(supportedDeviceForRepairRepository.getById(supportedDeviceForRepairId));
    }

    public SupportedDeviceForRepairResource getDeviceByTypeAndManufacturer(String manufacturer, DeviceType deviceType){
        if(deviceType == null){
            throw new RuntimeException("ERR: Device type is null");
        }

        return supportedDeviceForRepairMapper.toSupportedDeviceForRepairResource(supportedDeviceForRepairRepository.getByDeviceByTypeAndManufacturer(manufacturer, deviceType));
    }

    public List<SupportedDevicesProjection> getAllDevices(){
        return supportedDeviceForRepairRepository.getAllDevices();
    }

    @Override
    public List<SupportedDeviceForRepairResource> findAll(){
        return supportedDeviceForRepairMapper.toSupportedDeviceForRepairResources(supportedDeviceForRepairRepository.findAll());
    }

    @Override
    public SupportedDeviceForRepairResource save(SupportedDeviceForRepair supportedDeviceForRepair){
        return supportedDeviceForRepairMapper.toSupportedDeviceForRepairResource(supportedDeviceForRepairRepository.save(supportedDeviceForRepair));
    }

    @Override
    public void delete(SupportedDeviceForRepair device){
        supportedDeviceForRepairRepository.delete(device);
    }

    @Override
    public SupportedDeviceForRepairResource updateSupportedDeviceForRepair(SupportedDeviceForRepair device) throws NoSuchElementException {
        SupportedDeviceForRepair existingDevice = supportedDeviceForRepairRepository.findById(Long.valueOf(device.getId())).orElse(null);

        if(existingDevice != null){
            existingDevice.setDeviceType(device.getDeviceType());
            existingDevice.setManufacturer(device.getManufacturer());
            supportedDeviceForRepairRepository.save(existingDevice);
        }else{
            throw new NoSuchElementException("ERR: SupportedDeviceForRepair with ID " + device.getId() + " does not exist.");
        }

        return supportedDeviceForRepairMapper.toSupportedDeviceForRepairResource(existingDevice);
    }

    public SupportedDeviceForRepairResource addSupportedDevice(String deviceManufacturer, DeviceType deviceType){
        SupportedDeviceForRepairResource newSupportedDevice = new SupportedDeviceForRepairResource();
        newSupportedDevice.setDeviceType(deviceTypeMapper.toDeviceTypeResource(deviceType));
        newSupportedDevice.setManufacturer(deviceManufacturer);
        return supportedDeviceForRepairMapper.toSupportedDeviceForRepairResource(supportedDeviceForRepairRepository.save(supportedDeviceForRepairMapper.fromSupportedDeviceForRepairResource(newSupportedDevice)));
    }
}