package com.example.webdevshoes.mapper;

import com.example.webdevshoes.DTO.UserDTO;
import com.example.webdevshoes.DTO.UserRequestDTO;
import com.example.webdevshoes.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ReviewMapper.class})
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    User fromUserDTO(UserDTO user);
    User fromUserRequestDTO(UserRequestDTO user);
    UserDTO toUserDTO(User user);
    List<UserDTO> toUserDTOs(List<User> users);
}
