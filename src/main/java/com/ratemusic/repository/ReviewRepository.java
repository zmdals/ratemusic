package com.ratemusic.repository;

import com.ratemusic.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByAlbumId(Long albumId);
    boolean existsByUserIdAndAlbumId(Long userId, Long albumId);
    @Query("select avg(r.rate) from Review r where r.album.id = :albumId")
    Double getAvgRateByAlbumId(@Param("albumId")Long albumId);
}
