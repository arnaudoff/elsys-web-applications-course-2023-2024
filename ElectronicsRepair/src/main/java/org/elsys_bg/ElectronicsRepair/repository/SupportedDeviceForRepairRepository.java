package org.elsys_bg.ElectronicsRepair.repository;

import org.elsys_bg.ElectronicsRepair.entity.DeviceType;
import org.elsys_bg.ElectronicsRepair.entity.SupportedDeviceForRepair;
import org.elsys_bg.ElectronicsRepair.miscellaneous.SupportedDevicesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportedDeviceForRepairRepository extends JpaRepository<SupportedDeviceForRepair, Long>{
    @Query("SELECT sd.manufacturer AS manufacturer, dt.deviceType AS deviceType FROM SupportedDeviceForRepair sd " +
            "LEFT JOIN sd.deviceType dt")
    List<SupportedDevicesProjection> getAllDevices();

    @Query("SELECT sd FROM SupportedDeviceForRepair sd WHERE sd.deviceType = :deviceType AND sd.manufacturer = :manufacturer")
    SupportedDeviceForRepair getByDeviceByTypeAndManufacturer(String manufacturer, DeviceType deviceType);
}