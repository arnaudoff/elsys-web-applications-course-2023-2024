package me.bozhilov.EndMonitor.service;

import me.bozhilov.EndMonitor.controller.resources.AuthResp;
import me.bozhilov.EndMonitor.model.User;

public interface AuthService {
    User register(String username, String email, String password, String company);
    AuthResp login(String username, String password);
    User getCurrentUser();
}
