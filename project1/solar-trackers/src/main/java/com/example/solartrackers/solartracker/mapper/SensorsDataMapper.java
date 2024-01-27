package com.example.solartrackers.solartracker.mapper;

import com.example.solartrackers.solartracker.entity.SensorsData;
import com.example.solartrackers.solartracker.dto.AddSensorsDataRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SensorsDataMapper {
    SensorsDataMapper sensorsDataMapper = Mappers.getMapper(SensorsDataMapper.class);

    SensorsData fromCreateSensorsDataRequest(AddSensorsDataRequest sensorsData);
}
