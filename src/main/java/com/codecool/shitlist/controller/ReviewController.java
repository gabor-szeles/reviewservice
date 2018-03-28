package com.codecool.shitlist.controller;

import com.codecool.shitlist.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping(value = "/get-review/{userId}", produces = "application/json")
    public ResponseEntity getReview(@PathVariable("userId") Long userId) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String,String>> reviewsList = reviewService.getReviewsByUserId(userId);
        resultMap.put("userId", userId);
        resultMap.put("reviews", reviewsList);
        if(reviewsList.size()==0) {
            resultMap.put("found", false);
        } else{
            resultMap.put("found", true);
        }
        return new ResponseEntity(resultMap, HttpStatus.OK);
    }

    @PostMapping(value = "/add-review")
    public ResponseEntity addReview(@RequestParam("userId") Long userId,
                                    @RequestParam("authorId") Long authorId,
                                    @RequestParam("title") String title,
                                    @RequestParam("reviewText") String reviewText,
                                    @RequestParam("rating") Long rating) {
        reviewService.addReview(userId, authorId, title, reviewText, rating);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-review")
    public ResponseEntity deleteReview(@RequestParam("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity("OK", HttpStatus.OK);
    }
}


//Title
//Comment
//Date