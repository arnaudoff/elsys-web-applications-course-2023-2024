package org.elsys_bg.ElectronicsRepair.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.AdminResource;
import org.elsys_bg.ElectronicsRepair.entity.*;
import org.elsys_bg.ElectronicsRepair.miscellaneous.CustomFileReader;
import org.elsys_bg.ElectronicsRepair.service.impl.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admins")
@RequiredArgsConstructor
public class AdminController{
    private final AdminServiceImpl adminService;

    @GetMapping("/getAll")
    public ResponseEntity<String> getAllAdmins(){
        String htmlContent = "";

        try{
            htmlContent = adminService.findAll().toString();
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 500: Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(htmlContent, HttpStatus.OK);
    }

    @PostMapping("/admin_exists")
    public ResponseEntity<String> checkAdminExists(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode jsonNode = objectMapper.readTree(json);
            String username = jsonNode.get("user").asText();

            if(adminService.adminExists(username)){
                return new ResponseEntity<>("USER_EXISTS", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("USER_NOT_EXISTS", HttpStatus.OK);
            }
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 400: Bad Request", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admin_login")
    public ResponseEntity<String> adminLogIn(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode jsonNode = objectMapper.readTree(json);
            String username = jsonNode.get("user").asText();
            String password = jsonNode.get("pass").asText();

            if(adminService.checkAdminPassword(username, password)){
                return new ResponseEntity<>("USER_LOGGED_IN", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("ADMIN_PASSWORD_INCORRECT", HttpStatus.OK);
            }
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 400: Bad Request", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admin_signup")
    public ResponseEntity<String> adminSignUp(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode jsonNode = objectMapper.readTree(json);
            String username = jsonNode.get("user").asText();
            String password = jsonNode.get("pass").asText();
            AdminResource admin;

            admin = adminService.signUp(username, password);
            if(admin != null){
                return new ResponseEntity<>("ADMIN_SIGNED_UP", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("ADMIN_NOT_SIGNED_UP", HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(String.valueOf(e), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admin_homepage")
    public ResponseEntity<String> getAdminHomepage(@RequestParam("username") String username){
        String htmlContent = "";
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.TEXT_HTML);
        try{
            htmlContent = CustomFileReader.readFile(System.getProperty("user.dir") + "/src/main/java/org/elsys_bg/ElectronicsRepair/controller/resources/static/admin_homepage.html");
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 500: Internal server error", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        htmlContent = htmlContent.replace("__USERNAME__", username);

        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
    }
}