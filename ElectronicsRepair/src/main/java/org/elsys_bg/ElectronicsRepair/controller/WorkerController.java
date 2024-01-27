package org.elsys_bg.ElectronicsRepair.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.WorkerResource;
import org.elsys_bg.ElectronicsRepair.entity.Worker;
import org.elsys_bg.ElectronicsRepair.mapper.WorkerPostMapper;
import org.elsys_bg.ElectronicsRepair.miscellaneous.CustomFileReader;
import org.elsys_bg.ElectronicsRepair.service.impl.WorkerPostServiceImpl;
import org.elsys_bg.ElectronicsRepair.service.impl.WorkerServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/workers")
@RequiredArgsConstructor
public class WorkerController{
    private final WorkerServiceImpl workerService;
    private final WorkerPostServiceImpl workerPostService;
    private final WorkerPostMapper workerPostMapper;

    @GetMapping("/getAll")
    public ResponseEntity<String> getAllClients(){
        String htmlContent = "";

        try{
            htmlContent = workerService.findAll().toString();
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 500: Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(htmlContent, HttpStatus.OK);
    }

    @PostMapping("/worker_exists")
    public ResponseEntity<String> checkWorkerExists(@RequestBody String json) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            String username = jsonNode.get("user").asText();

            if (workerService.workerExists(username)) {
                return new ResponseEntity<>("USER_EXISTS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("USER_NOT_EXISTS", HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Error 400: Bad Request", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/worker_login")
    public ResponseEntity<String> workerLogIn(@RequestBody String json) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            String username = jsonNode.get("user").asText();
            String password = jsonNode.get("pass").asText();

            if (workerService.checkWorkerPassword(username, password)) {
                return new ResponseEntity<>("USER_LOGGED_IN", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("WORKER_PASSWORD_INCORRECT", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Error 400: Bad Request", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add_worker")
    public ResponseEntity<String> addWorker(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode jsonNode = objectMapper.readTree(json);
            String workerPost = jsonNode.get("workerPost").asText();
            String workerName = jsonNode.get("workerName").asText();
            String workerPassword = jsonNode.get("workerPassword").asText();

            WorkerResource post = workerService.signUp(workerName, workerPassword, workerPostMapper.fromWorkerPostResource(workerPostService.getByPost(workerPost)));
            if(post != null){
                return new ResponseEntity<>("WORKER__ADDED", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("WORKER_NOT_ADDED", HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(String.valueOf(e), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/worker_homepage")
    public ResponseEntity<String> getWorkerHomepage(@RequestParam("username") String username) {
        String htmlContent = "";
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.TEXT_HTML);
        try {
            htmlContent = CustomFileReader.readFile(System.getProperty("user.dir") + "/src/main/java/org/elsys_bg/ElectronicsRepair/controller/resources/static/worker_homepage.html");
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Error 500: Internal server error", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        htmlContent = htmlContent.replace("__WORKER_NAME__", username);

        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
    }
}