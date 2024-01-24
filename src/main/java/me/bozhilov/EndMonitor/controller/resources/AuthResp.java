package me.bozhilov.EndMonitor.controller.resources;

import lombok.Data;

@Data
public class AuthResp {
    private String id;
    private String username;
    private String email;
    private String company;
    private String token;
}
