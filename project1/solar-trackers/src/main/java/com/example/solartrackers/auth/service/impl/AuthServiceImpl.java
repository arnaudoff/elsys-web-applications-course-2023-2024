package com.example.solartrackers.auth.service.impl;

import com.example.solartrackers.auth.dto.AuthResponse;
import com.example.solartrackers.auth.dto.SignInRequest;
import com.example.solartrackers.auth.dto.SignUpRequest;
import com.example.solartrackers.auth.service.AuthService;
import com.example.solartrackers.auth.service.JwtService;
import com.example.solartrackers.shared.exception.EmailAlreadyUsedException;
import com.example.solartrackers.shared.exception.InvalidCredentialsException;
import com.example.solartrackers.user.entity.User;
import com.example.solartrackers.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.solartrackers.user.mapper.UserMapper.userMapper;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    private final JwtService jwtService;

    @Override
    public AuthResponse signUp(SignUpRequest signUpData) {
        User user = userMapper.fromSignUpRequest(signUpData);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            User createdUser = userService.createUser(user);
            String token = jwtService.generateToken(createdUser.getEmail());

            return userMapper.toAuthResponse(createdUser, token);
        } catch (DataIntegrityViolationException e) {
            throw new EmailAlreadyUsedException();
        }
    }

    @Override
    public AuthResponse signIn(SignInRequest signInData) {
        User user = userService.getUser(signInData.getEmail());

        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!passwordEncoder.matches(signInData.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtService.generateToken(user.getEmail());

        return userMapper.toAuthResponse(user, token);
    }
}
