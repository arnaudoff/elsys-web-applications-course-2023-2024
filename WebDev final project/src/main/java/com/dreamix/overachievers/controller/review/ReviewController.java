package com.dreamix.overachievers.controller.review;

import com.dreamix.overachievers.dto.exception.ExceptionDto;
import com.dreamix.overachievers.dto.review.ReviewDto;
import com.dreamix.overachievers.entity.Review;
import com.dreamix.overachievers.service.review.ReviewService;
import com.dreamix.overachievers.vo.ReviewType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Review", description = "Reviews (feedbacks/commends) endpoints")
@RestController
@RequestMapping("/reviews")
@Slf4j
@CrossOrigin
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) { this.reviewService = reviewService; }

    @Operation(
            summary = "Get commends",
            description = "Get reviews of type commend by the receiver employee id",
            tags = { "commend", "review", "get", "by id" })
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Successfully fetched commend",
                    content = { @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Review.class)),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "500",
                    description = "Incorrect request or serverside error",
                    content = { @Content() }) })
    @Parameters(value = { @Parameter(name = "employeeId", description = "The receiver employee id") })
    @GetMapping("/{employeeId}/commends")
    public List<ReviewDto> getCommends(@PathVariable Long employeeId) {
        log.info("Fetching of commends of uuid " + employeeId + " started");
        return reviewService.getAllReviewsForEmployee(employeeId, ReviewType.COMMEND);
    }

    @Operation(
            summary = "Get feedbacks",
            description = "Get reviews of type feedback by the receiver employee id",
            tags = { "feedback", "review", "get", "by id" })
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Successfully fetched feedback",
                    content = { @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Review.class)),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "500",
                    description = "Incorrect request or serverside error",
                    content = { @Content() }) })
    @Parameters(value = { @Parameter(name = "employeeId", description = "The receiver employee id") })
    @GetMapping("/{employeeId}/feedbacks")
    public List<ReviewDto> getFeedbacks(@PathVariable Long employeeId) {
        log.info("Fetching of feedbacks by uuid " + employeeId + " started");
        return reviewService.getAllReviewsForEmployee(employeeId, ReviewType.FEEDBACK);
    }

    @Operation(
            summary = "Create review",
            description = "Create a review of type commend or feedback",
            tags = { "review", "commend", "feedback", "post", "create" })
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Successfully created review",
                    content = { @Content() }),
            @ApiResponse(responseCode = "409",
                    description = "Review with this video URL already exists",
                    content = { @Content(
                            schema = @Schema(implementation = ExceptionDto.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "500",
                    description = "Incorrect request or serverside error",
                    content = { @Content() }) })
    @Parameters(value = { @Parameter(name = "reviewDto",
            description = "The review DTO (without id & with necessary review type) for the review entity") })
    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody @Validated ReviewDto reviewDto) {
        log.info("Creating new review");
        return reviewService.createReview(reviewDto);
    }

}