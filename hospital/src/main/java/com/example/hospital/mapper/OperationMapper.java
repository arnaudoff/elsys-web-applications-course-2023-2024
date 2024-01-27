package com.example.hospital.mapper;

import com.example.hospital.controller.resource.OperationResource;
import com.example.hospital.entity.Operation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OperationMapper {
    OperationMapper OPERATION_MAPPER = Mappers.getMapper(OperationMapper.class);

    OperationResource toOperationResource(Operation operation);

    Operation fromOperationResource(OperationResource operationResource);

    List<OperationResource> toOperationResources(List<Operation> operations);

    List<Operation> fromOperationResources(List<OperationResource> operationResources);
}
