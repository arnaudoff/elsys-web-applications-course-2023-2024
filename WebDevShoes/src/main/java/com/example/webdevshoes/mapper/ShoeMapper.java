package com.example.webdevshoes.mapper;

import com.example.webdevshoes.DTO.ShoeDTO;
import com.example.webdevshoes.entity.Shoe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ReviewMapper.class})
public interface ShoeMapper {
    ShoeMapper SHOE_MAPPER = Mappers.getMapper(ShoeMapper.class);

    Shoe fromShoeDTO(ShoeDTO shoeDTO);
    ShoeDTO toShoeDTO(Shoe shoe);
    List<ShoeDTO> toShoeDTOs(List<Shoe> shoes);
}
