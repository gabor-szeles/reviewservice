package com.codecool.shitlist.service;

import com.codecool.shitlist.model.Review;
import com.codecool.shitlist.repository.ReviewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {

    private final String userServiceURI = "https://shitwish-user.herokuapp.com/user/";

    @Autowired
    private ReviewRepository reviewRepository;

    public void addReview(Long userId, Long authorId, String title, String reviewText, Long rating, String comment) {
        Review toAdd = new Review(authorId, title, reviewText, userId, rating, comment);
        reviewRepository.save(toAdd);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.delete(reviewId);
    }

    public List<Map<String,String>> getReviewsByUserId(Long userId) {
        List<Map<String,String>> resultList = new ArrayList<>();
        List<Review> reviewList = reviewRepository.findAllByUserId(userId);
        for (Review review:reviewList) {
            Map<String, String> reviewMap = new HashMap<>();
            reviewMap.put("reviewId", review.getId().toString());
            reviewMap.put("author", getAuthorNameById(review.getAuthorId()));
            reviewMap.put("title", review.getTitle());
            reviewMap.put("description", review.getDescription());
            reviewMap.put("rating", review.getRating().toString());
            reviewMap.put("comment", review.getComment());
            reviewMap.put("date", review.getDate().toString());
            resultList.add(reviewMap);
        }
        return resultList;
    }

    private String getAuthorNameById(Long authorId) {
        ObjectMapper objectMapper = new ObjectMapper();
        String fullURI = userServiceURI.concat(authorId.toString());
        try {
            Map<String, String> returnedMap = objectMapper.readValue(new URL(fullURI), HashMap.class);
            return returnedMap.get("userName");
        } catch (IOException e) {
            return "Service not available";
        }
    }
}
