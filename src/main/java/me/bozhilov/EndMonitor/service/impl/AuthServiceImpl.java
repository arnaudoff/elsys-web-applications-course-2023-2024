package me.bozhilov.EndMonitor.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.bozhilov.EndMonitor.controller.resources.AuthResp;
import me.bozhilov.EndMonitor.controller.resources.UserResource;
import me.bozhilov.EndMonitor.model.User;
import me.bozhilov.EndMonitor.repository.UserRepository;
import me.bozhilov.EndMonitor.service.AuthService;
import me.bozhilov.EndMonitor.service.CompanyService;
import me.bozhilov.EndMonitor.service.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static me.bozhilov.EndMonitor.mapper.CompanyMapper.COMPANY_MAPPER;
import static me.bozhilov.EndMonitor.mapper.UserMapper.userMapper;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CompanyService companyService;


    @Override
    public User register(String username, String email, String password, String companyInput) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (companyInput == null || companyInput.isEmpty()) {
            throw new IllegalArgumentException("Company cannot be empty");
        }

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email is already taken");
        }

        UserResource userResource = new UserResource();
        userResource.setUsername(username);
        userResource.setEmail(email);
        userResource.setPassword(passwordEncoder.encode(password));
        userResource.setCompany(companyInput);

        User user = userMapper.fromUserResource(userResource);
        companyService.findByName(user.getCompany().getName())
                .ifPresentOrElse(
                        company -> user.setCompany(COMPANY_MAPPER.fromCompanyResource(company)),
                        () -> {
                            throw new IllegalArgumentException(
                                    "Company with name " + user.getCompany().getName() + " not found");
                        });
        return userRepository.save(user);
    }

    @Override
    public AuthResp login(String username, String password) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        User user;

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password
                    )
            )

            Optional<User> userOptional = userRepository.findByUsername(username);

            if (userOptional.isEmpty()) {
                throw new EntityNotFoundException("User not found");
            }

            user = userOptional.get();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String jwt = jwtService.createToken(user.getUsername());

        AuthResp authResp = new AuthResp();
        authResp.setToken(jwt);
        authResp.setUsername(user.getUsername());
        authResp.setEmail(user.getEmail());
        authResp.setCompany(user.getCompany().getName());

        return authResp;
    }

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
