package com.example.webdevshoes.service;

import com.example.webdevshoes.DTO.UserDTO;
import com.example.webdevshoes.DTO.UserRequestDTO;
import com.example.webdevshoes.DTO.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAll();
    UserDTO getById(Long id);
    UserResponseDTO save(UserRequestDTO user);
    UserDTO update(UserDTO user, Long id);
    void delete(Long id);
    UserDTO getByUsername(String username);
    UserResponseDTO login(UserRequestDTO user);
}
