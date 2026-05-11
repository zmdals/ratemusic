package com.ratemusic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SongRequest {

    @NotBlank(message = "곡 이름은 필수 값 입니다.")
    private String title;

    private int trackNumber;
    private int duration;
}