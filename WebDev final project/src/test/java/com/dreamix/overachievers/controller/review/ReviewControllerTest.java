package com.dreamix.overachievers.controller.review;

import com.dreamix.overachievers.dto.employee.EmployeeDto;
import com.dreamix.overachievers.dto.review.ReviewDto;
import com.dreamix.overachievers.service.review.ReviewService;
import com.dreamix.overachievers.vo.ReviewType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    void getCommendsTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/reviews/{id}/commends", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getFeedbacksTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/reviews/{id}/feedbacks", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createReviewTest() throws Exception {

        EmployeeDto receiver = new EmployeeDto();
        receiver.setId(1L);
        EmployeeDto sender = new EmployeeDto();
        sender.setId(1L);

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReceiver(receiver);
        reviewDto.setSender(sender);
        reviewDto.setType(ReviewType.FEEDBACK);
        reviewDto.setVideoUrl("ewkfdfsdcdscwd.mp4");

        mvc.perform(MockMvcRequestBuilders
                        .post("/reviews")
                        .content(new ObjectMapper().writeValueAsString(reviewDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}