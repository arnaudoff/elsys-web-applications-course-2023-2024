package me.bozhilov.EndMonitor.service;

public interface JWTService {
    String createToken(String username);

    boolean validateToken(String token, String username);

    String getUsernameFromToken(String token);

    boolean isTokenExpired(String token);
}
