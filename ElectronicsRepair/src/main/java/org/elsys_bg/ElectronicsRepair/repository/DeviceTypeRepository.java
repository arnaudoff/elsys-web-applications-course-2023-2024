package org.elsys_bg.ElectronicsRepair.repository;

import org.elsys_bg.ElectronicsRepair.entity.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Long>{
    @Query("SELECT dt FROM DeviceType dt WHERE dt.deviceType = :deviceType")
    DeviceType getByDevice(@Param("deviceType") String deviceType);
}