package com.example.webdevshoes.service.impl;

import com.example.webdevshoes.DTO.UserDTO;
import com.example.webdevshoes.DTO.UserRequestDTO;
import com.example.webdevshoes.DTO.UserResponseDTO;
import com.example.webdevshoes.entity.Review;
import com.example.webdevshoes.entity.User;
import com.example.webdevshoes.repository.ReviewRepository;
import com.example.webdevshoes.repository.ShoeRepository;
import com.example.webdevshoes.repository.UserRepository;
import com.example.webdevshoes.security.JWTGenerator;
import com.example.webdevshoes.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.webdevshoes.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ShoeRepository shoeRepository;
    private final ReviewRepository reviewRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    @Override
    public List<UserDTO> getAll() {
        return USER_MAPPER.toUserDTOs(userRepository.findAll());
    }

    @Override
    public UserDTO getById(Long id) {
        return USER_MAPPER.toUserDTO(userRepository.getReferenceById(id));
    }

    @Override
    public UserResponseDTO save(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
            throw new DuplicateKeyException("Username already exists");
        }

        User userToSave = USER_MAPPER.fromUserRequestDTO(userRequestDTO);
        userToSave.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        userToSave.setShoes(null);
        userToSave.setReviews(null);

        userRepository.save(userToSave);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUsername(userToSave.getUsername());
        userResponseDTO.setToken(jwtGenerator.generateToken(userToSave));

        return userResponseDTO;
    }

    @Override
    public UserDTO update(UserDTO userDTO, Long id) {
        User userToUpdate = userRepository.getReferenceById(id);

        userToUpdate.setUsername(userDTO.getUsername());

        return USER_MAPPER.toUserDTO(userRepository.save(userToUpdate));
    }

    @Override
    public void delete(Long id) {
        List<Review> reviewToDelete = reviewRepository.findAllByUserId(id);
        reviewRepository.deleteAll(reviewToDelete);

        removeUserFromShoes(id);

        userRepository.deleteById(id);
    }

    private void removeUserFromShoes(Long id) {
        shoeRepository.findAllByUsersId(id).forEach(shoe -> {
            shoe.getUsers().removeIf(user -> user.getId().equals(id));
            shoeRepository.save(shoe);
        });
    }

    @Override
    public UserDTO getByUsername(String username) {
        return USER_MAPPER.toUserDTO(userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("User not found"))
        );
    }

    @Override
    public UserResponseDTO login(UserRequestDTO userRequestDTO) {
        User user;
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequestDTO.getUsername(),
                            userRequestDTO.getPassword()
                    )
            );
            user = (User) authentication.getPrincipal();
        } catch (AuthenticationException e) {
            if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
                throw new BadCredentialsException("Wrong password!", e);
            } else {
                throw new BadCredentialsException("Wrong username!", e);
            }
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setToken(jwtGenerator.generateToken(user));

        return userResponseDTO;
    }
}
