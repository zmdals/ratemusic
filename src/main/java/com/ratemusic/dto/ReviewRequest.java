package com.ratemusic.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Getter
public class ReviewRequest {

    private String comment;

    @NotNull(message = "평점은 필수 값 입니다.")
    @Range(min = 0, max = 5)
    private BigDecimal rate;
}
