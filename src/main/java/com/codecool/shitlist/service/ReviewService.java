package com.codecool.shitlist.service;

import com.codecool.shitlist.model.Review;
import com.codecool.shitlist.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public void addReview(Long userId, Long authorId, String reviewText) {
        Review toAdd = new Review(authorId, reviewText, userId);
        reviewRepository.save(toAdd);
    }
}