package org.elsys_bg.ElectronicsRepair.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.ClientResource;
import org.elsys_bg.ElectronicsRepair.mapper.ClientMapper;
import org.elsys_bg.ElectronicsRepair.miscellaneous.CustomFileReader;
import org.elsys_bg.ElectronicsRepair.service.impl.ClientContactServiceImpl;
import org.elsys_bg.ElectronicsRepair.service.impl.ClientServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/clients")
@RequiredArgsConstructor
public class ClientController{
    private final ClientServiceImpl clientService;
    private final ClientContactServiceImpl clientContactsService;
    private final ClientMapper clientMapper;

    @GetMapping("/getAll")
    public ResponseEntity<String> getAllClients(){
        String htmlContent = "";

        try{
            htmlContent = clientService.findAll().toString();
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 500: Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(htmlContent, HttpStatus.OK);
    }

    @PostMapping("/client_exists")
    public ResponseEntity<String> checkUserExists(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode jsonNode = objectMapper.readTree(json);
            String username = jsonNode.get("user").asText();

            if(clientService.userExists(username)){
                return new ResponseEntity<>("USER_EXISTS", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("USER_NOT_EXISTS", HttpStatus.OK);
            }
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 400: Bad Request", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/client_login")
    public ResponseEntity<String> clientLogIn(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode jsonNode = objectMapper.readTree(json);
            String username = jsonNode.get("user").asText();
            String password = jsonNode.get("pass").asText();

            if(clientService.checkClientPassword(username, password)){
                return new ResponseEntity<>("USER_LOGGED_IN", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("CLIENT_PASSWORD_INCORRECT", HttpStatus.OK);
            }
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 400: Bad Request", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/client_signup")
    public ResponseEntity<String> clientSignUp(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode jsonNode = objectMapper.readTree(json);
            String username = jsonNode.get("user").asText();
            String password = jsonNode.get("pass").asText();
            String email = jsonNode.get("email").asText();
            String tel = jsonNode.get("tel").asText();
            ClientResource client;

            client = clientService.signUp(username, password);
            if(client != null){
                if(clientContactsService.addContact(clientMapper.fromClientResource(client), email, tel) != null){
                    return new ResponseEntity<>("USER_SIGNED_UP", HttpStatus.OK);
                }else{
                    clientService.deleteClientByName(client.getName());
                    return new ResponseEntity<>("USER_CONTACT_NOT_INSERTED", HttpStatus.BAD_REQUEST);
                }
            }else{
                return new ResponseEntity<>("CLIENT_NOT_SIGNED_UP", HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(String.valueOf(e), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/client_homepage")
    public ResponseEntity<String> getClientHomepage(@RequestParam("username") String username){
        String htmlContent = "";
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.TEXT_HTML);
        try{
            htmlContent = CustomFileReader.readFile(System.getProperty("user.dir") + "/src/main/java/org/elsys_bg/ElectronicsRepair/controller/resources/static/client_homepage.html");
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 500: Internal server error", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        htmlContent = htmlContent.replace("__CLIENT_NAME__", username);

        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
    }
}