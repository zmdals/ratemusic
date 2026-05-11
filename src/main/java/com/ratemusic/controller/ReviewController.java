package com.ratemusic.controller;

import com.ratemusic.dto.ReviewRequest;
import com.ratemusic.dto.ReviewResponse;
import com.ratemusic.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums/{albumId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getALlReviews(@PathVariable Long albumId){
        return ResponseEntity.ok(reviewService.getAllReviews(albumId));
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@PathVariable Long albumId,
                                                       @Valid @RequestBody ReviewRequest request,
                                                       Authentication authentication){
        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.createReview(userId, albumId, request));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable Long reviewId,
                                                       @Valid @RequestBody ReviewRequest request,
                                                       Authentication authentication){
        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(reviewService.updateReview(userId,reviewId,request));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId,
                                             Authentication authentication){
        Long userId = (Long) authentication.getPrincipal();
        reviewService.deleteReview(userId, reviewId);
        return ResponseEntity.noContent().build();
    }
}
