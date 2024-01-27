package org.elsys_bg.ElectronicsRepair.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elsys_bg.ElectronicsRepair.miscellaneous.CustomFileReader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class MainController{
    @GetMapping
    public ResponseEntity<String> mainPage(){
        String htmlContent = "";
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.TEXT_HTML);
        try{
            htmlContent = CustomFileReader.readFile(System.getProperty("user.dir") + "/src/main/java/org/elsys_bg/ElectronicsRepair/controller/resources/static/main.html");
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 500: Internal server error", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
    }

    @RequestMapping("/signin")
    public ResponseEntity<String> redirectToSignInPage(@RequestParam("role") String role){
        String htmlContent = "";
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.TEXT_HTML);
        try{
            htmlContent = CustomFileReader.readFile(System.getProperty("user.dir") + "/src/main/java/org/elsys_bg/ElectronicsRepair/controller/resources/static/signin.html");

            if(!role.equals("client") && !role.equals("worker") && !role.equals("admin")){
                throw new RuntimeException("ERR: Given user role does not exist");
            }
        }catch(Exception e){
            System.out.println(e);

            if(e.getMessage().equals("ERR: Given user role does not exist")){
                return new ResponseEntity<>("Error 404: Given user role does not exist", headers, HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>("Error 500: Internal server error", headers, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        htmlContent = htmlContent.replace("__USER_ROLE__", role);

        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
    }

    @RequestMapping("/check_sql_injections")
    public ResponseEntity<String> checkForSQLInjections(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode jsonNode = objectMapper.readTree(json);
            String username = jsonNode.get("user").asText();
            String password = jsonNode.get("pass").asText();

            if(isSqlInjectionDetected(username, password)){
                return new ResponseEntity<>("INJECTIONS", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("NO_INJECTIONS", HttpStatus.OK);
            }
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 400: Bad Request", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/check_string_sql_injections")
    public ResponseEntity<String> checkStringForSQLInjections(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode jsonNode = objectMapper.readTree(json);
            String string = jsonNode.get("string").asText();

            if(isSqlInjectionDetected(string)){
                return new ResponseEntity<>("INJECTIONS", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("NO_INJECTIONS", HttpStatus.OK);
            }
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 400: Bad Request", HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isSqlInjectionDetected(String username, String password){
        String[] sqlKeywords = {"DROP", "DELETE", "SELECT", "UPDATE", "INSERT"};
        String[] maliciousCharacters = {"'", "\"", ";", "--"};

        for(String keyword : sqlKeywords){
            if(username.toUpperCase().contains(keyword)){
                return true;
            }
        }
        for(String character : maliciousCharacters){
            if(username.contains(character)){
                return true;
            }
        }

        for(String keyword : sqlKeywords){
            if(password.toUpperCase().contains(keyword)){
                return true;
            }
        }
        for(String character : maliciousCharacters){
            if(password.contains(character)){
                return true;
            }
        }
        return false;
    }

    private boolean isSqlInjectionDetected(String string){
        String[] sqlKeywords = {"DROP", "DELETE", "SELECT", "UPDATE", "INSERT"};
        String[] maliciousCharacters = {"'", "\"", ";", "--"};

        for(String keyword : sqlKeywords){
            if(string.toUpperCase().contains(keyword)){
                return true;
            }
        }
        for(String character : maliciousCharacters){
            if(string.contains(character)){
                return true;
            }
        }

        for(String keyword : sqlKeywords){
            if(string.toUpperCase().contains(keyword)){
                return true;
            }
        }
        for(String character : maliciousCharacters){
            if(string.contains(character)){
                return true;
            }
        }
        return false;
    }
}