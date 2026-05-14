package com.ratemusic.controller;

import com.ratemusic.dto.ReviewRequest;
import com.ratemusic.dto.ReviewResponse;
import com.ratemusic.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Review", description = "앨범 리뷰 API")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "앨범 리뷰 전체 조회", description = "albumId와 일치하는 앨범의 전체 리뷰를 조회합니다.")
    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getALlReviews(@PathVariable Long albumId){
        return ResponseEntity.ok(reviewService.getAllReviews(albumId));
    }

    @Operation(summary = "앨범 리뷰 작성", description = "userId로 중복 리뷰 여부 판별 후," +
            "albumId와 일치하는 앨범에 리뷰를 생성합니다.")
    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@PathVariable Long albumId,
                                                       @Valid @RequestBody ReviewRequest request,
                                                       Authentication authentication){
        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.createReview(userId, albumId, request));
    }

    @Operation(summary = "앨범 리뷰 수정", description = "userId로 본인 리뷰 여부 판별 후," +
            "reviewId와 일치하는 리뷰를 수정합니다.")
    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable Long reviewId,
                                                       @Valid @RequestBody ReviewRequest request,
                                                       Authentication authentication){
        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(reviewService.updateReview(userId,reviewId,request));
    }

    @Operation(summary = "앨범 리뷰 삭제", description = "userId로 본인 리뷰 여부 판별 후," +
            "reviewId와 일치하는 리뷰를 삭제합니다.")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId,
                                             Authentication authentication){
        Long userId = (Long) authentication.getPrincipal();
        reviewService.deleteReview(userId, reviewId);
        return ResponseEntity.noContent().build();
    }
}
