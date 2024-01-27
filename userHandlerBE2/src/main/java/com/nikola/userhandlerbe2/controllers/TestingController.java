package com.nikola.userhandlerbe2.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userHandler/v2/testing")
public class TestingController {
    @RequestMapping("/test")
    public String test() {
        return "Hello World!";
    }
}
