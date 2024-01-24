package org.elsys_bg.ElectronicsRepair.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.*;
import org.elsys_bg.ElectronicsRepair.mapper.ReviewMapper;
import org.elsys_bg.ElectronicsRepair.service.impl.ClientServiceImpl;
import org.elsys_bg.ElectronicsRepair.service.impl.ReviewServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController{
    private final ReviewServiceImpl reviewService;
    private final ReviewMapper reviewMapper;
    private final ClientServiceImpl clientService;

    @GetMapping("/getAll")
    public ResponseEntity<String> getAllReview(){
        StringBuilder htmlContent = new StringBuilder();

        try{
            List<ReviewResource> reviews = reviewService.findAll();

            String joinedContent = reviews.stream().map(review -> review.getClient().getId() + "---" +
                            review.getClient().getName() + "---" +
                            review.getReviewText())
                    .collect(Collectors.joining("-;-;-"));
            htmlContent.append(joinedContent);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 500: Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(htmlContent.toString(), HttpStatus.OK);
    }

    @PostMapping("/add_review")
    public ResponseEntity<String> addReview(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode jsonNode = objectMapper.readTree(json);

            ClientResource client = clientService.getByName(jsonNode.get("clientName").asText());
            String reviewText = jsonNode.get("reviewText").asText();

            if(client == null){
                throw new RuntimeException("ERR: Given client does not exist");
            }

            ReviewResource reviewResource = new ReviewResource();
            reviewResource.setClient(client);
            reviewResource.setReviewText(reviewText);

            ReviewResource review = reviewService.save(reviewMapper.fromReviewResource(reviewResource));
            if(review != null){
                return new ResponseEntity<>("REVIEW_ADDED", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("REVIEW_NOT_ADDED", HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            System.out.println("ERR: " + e.getMessage() + "\nStack Trace: " + e.getStackTrace().toString());
            return new ResponseEntity<>("Error 500: Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}