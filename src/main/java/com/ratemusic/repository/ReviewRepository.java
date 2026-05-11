package com.ratemusic.repository;

import com.ratemusic.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByAlbumId(Long albumId);
    boolean existsByUserIdAndAlbumId(Long userId, Long albumId);
}
