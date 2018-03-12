package com.codecool.shitlist.controller;

import com.codecool.shitlist.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping(value = "/getreview")
    public ResponseEntity getReview() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("test", "test");
        return new ResponseEntity(testMap, HttpStatus.OK);
    }

    @PutMapping(value = "/addreview")
    public ResponseEntity addReview(@RequestParam("userId") Long userId,
                                    @RequestParam("authorId") Long authorId,
                                    @RequestParam("review") String reviewText) {
        reviewService.addReview(userId, authorId, reviewText);
        return new ResponseEntity("OK", HttpStatus.OK);
    }
}
