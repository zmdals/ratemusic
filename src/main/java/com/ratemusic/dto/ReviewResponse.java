package com.ratemusic.dto;

import com.ratemusic.entity.Review;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ReviewResponse {

    private Long id;
    private Long userId;
    private Long albumId;
    private String nickname;
    private String comment;
    private BigDecimal rate;

    public ReviewResponse(Review review){
        this.id = review.getId();
        this.userId = review.getUser().getId();
        this.albumId = review.getAlbum().getId();
        this.nickname = review.getUser().getNickname();
        this.comment = review.getComment();
        this.rate = review.getRate();
    }
}
