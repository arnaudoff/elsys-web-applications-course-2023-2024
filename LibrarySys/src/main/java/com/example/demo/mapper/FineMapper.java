package com.example.demo.mapper;

import com.example.demo.controlers.resources.UserRes;
import com.example.demo.entities.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.example.demo.entities.Fine;
import com.example.demo.controlers.resources.FineRes;

import java.util.List;

@Mapper(uses = UserMapper.class)
public interface FineMapper {
    FineMapper FINE_MAPPER = Mappers.getMapper(FineMapper.class);

    @Mapping(target = "user", source = "user", qualifiedByName = "mapUserToUserRes")
    Fine fineToResuorce(FineRes resource);

    @InheritInverseConfiguration
    @Mapping(target = "user", source = "user", qualifiedByName = "mapUserToUserRes")
    FineRes fineToFineRes(Fine fine);

    List<FineRes> toFineResList(List<Fine> fines);

    @Named("mapUserToUserRes")
    default UserRes mapUserToUserRes(User user) {
        return UserMapper.mapUserToUserRes(user);
    }

}
