package com.example.solartrackers.location.mapper;

import com.example.solartrackers.location.dto.AddLocationRequest;
import com.example.solartrackers.location.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocationMapper {
    LocationMapper locationMapper = Mappers.getMapper(LocationMapper.class);

    @Mapping(target = "solarTrackers", ignore = true)
    Location fromCreateLocationRequest(AddLocationRequest locationData);
}
