package com.ratemusic.service;

import com.ratemusic.dto.ReviewRequest;
import com.ratemusic.dto.ReviewResponse;
import com.ratemusic.entity.Album;
import com.ratemusic.entity.Review;
import com.ratemusic.entity.User;
import com.ratemusic.exception.NotFoundException;
import com.ratemusic.repository.AlbumRepository;
import com.ratemusic.repository.ReviewRepository;
import com.ratemusic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;

    @Transactional(readOnly = true)
    public List<ReviewResponse> getAllReviews(Long albumId){
        return reviewRepository.findByAlbumId(albumId)
                .stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReviewResponse createReview(Long userId, Long albumId, ReviewRequest request){
        if(reviewRepository.existsByUserIdAndAlbumId(userId,albumId)){
            throw new IllegalArgumentException("리뷰는 앨범당 한 개만 작성할 수 있습니다.");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 앨범입니다."));

        Review review = new Review(user, album, request.getComment(), request.getRate());
        reviewRepository.save(review);
        return new ReviewResponse(review);
    }

    @Transactional
    public ReviewResponse updateReview(Long userId, Long reviewId, ReviewRequest request){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 리뷰입니다."));
        if(!review.getUser().getId().equals(userId)){
            throw new IllegalArgumentException("본인의 리뷰만 수정할 수 있습니다.");
        }
        review.update(request.getComment(), request.getRate());
        return new ReviewResponse(review);
    }

    public void deleteReview(Long userId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 리뷰입니다."));
        if(!review.getUser().getId().equals(userId)){
            throw new IllegalArgumentException("본인의 리뷰만 삭제할 수 있습니다.");
        }
        reviewRepository.deleteById(reviewId);
    }
}
