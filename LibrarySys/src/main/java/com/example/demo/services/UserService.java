package com.example.demo.services;

import com.example.demo.controlers.resources.UserRes;
import com.example.demo.entities.User;

import java.util.List;

public interface UserService {
    List<UserRes> AllUsers();
    UserRes findById(Long id);
    UserRes save(UserRes user);
    UserRes update(User user, Long id);
    void deleteById(Long id);
}
