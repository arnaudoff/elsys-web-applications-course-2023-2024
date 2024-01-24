package com.example.solartrackers.solartracker.mapper;

import com.example.solartrackers.solartracker.entity.SolarTracker;
import com.example.solartrackers.solartracker.dto.CreateSTRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SolarTrackerMapper {
    SolarTrackerMapper solarTrackerMapper = Mappers.getMapper(SolarTrackerMapper.class);

    SolarTracker fromCreateSTRequest(CreateSTRequest stData);
}
