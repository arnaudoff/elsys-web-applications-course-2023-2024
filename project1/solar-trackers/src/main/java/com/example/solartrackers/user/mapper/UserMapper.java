package com.example.solartrackers.user.mapper;

import com.example.solartrackers.auth.dto.AuthResponse;
import com.example.solartrackers.auth.dto.SignUpRequest;
import com.example.solartrackers.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    User fromSignUpRequest(SignUpRequest signUpRequest);

    @Mapping(target = "user", source = "user")
    @Mapping(target = "token", source = "token")
    AuthResponse toAuthResponse(User user, String token);
}
