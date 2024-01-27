package org.elsys_bg.ElectronicsRepair.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.WorkerPostResource;
import org.elsys_bg.ElectronicsRepair.service.impl.WorkerPostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/worker_posts")
@RequiredArgsConstructor
public class WorkerPostController{
    private final WorkerPostServiceImpl workerPostService;

    @GetMapping("/getAll")
    public ResponseEntity<String> getAllClients(){
        StringBuilder htmlContentBuilder = new StringBuilder();

        try{
            List<WorkerPostResource> workerPosts = workerPostService.findAll();
            workerPosts.forEach(post -> {
                htmlContentBuilder.append(post.getPost()).append(";");
            });
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 500: Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String htmlContent = htmlContentBuilder.toString();
        return new ResponseEntity<>(htmlContent, HttpStatus.OK);
    }

    @PostMapping("/add_worker_post")
    public ResponseEntity<String> addWorkerPost(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode jsonNode = objectMapper.readTree(json);
            String workerPost = jsonNode.get("workerPost").asText();

            WorkerPostResource post = workerPostService.addWorkerPost(workerPost);
            if(post != null){
                return new ResponseEntity<>("WORKER_POST_ADDED", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("WORKER_POST_NOT_ADDED", HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(String.valueOf(e), HttpStatus.BAD_REQUEST);
        }
    }
}