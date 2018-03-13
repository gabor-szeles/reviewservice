package com.codecool.shitlist.controller;

import com.codecool.shitlist.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping(value = "/get-review/{userId}")
    public ResponseEntity getReview(@PathVariable("userId") Long userId) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("reviews", reviewService.getReviewsByUserId(userId));
        return new ResponseEntity(resultMap, HttpStatus.OK);
    }

    @PutMapping(value = "/add-review")
    public ResponseEntity addReview(@RequestParam("userId") Long userId,
                                    @RequestParam("authorId") Long authorId,
                                    @RequestParam("reviewText") String reviewText) {
        reviewService.addReview(userId, authorId, reviewText);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-review")
    public ResponseEntity deleteReview(@RequestParam("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity("OK", HttpStatus.OK);
    }
}
