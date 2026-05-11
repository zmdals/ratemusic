package com.ratemusic.dto;

import com.ratemusic.entity.AlbumType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AlbumRequest {

    @NotBlank(message = "앨범 제목은 필수 값 입니다.")
    private String title;

    @NotBlank(message = "앨범 아티스트는 필수 값 입니다.")
    private String artist;

    private String description;

    private String genre;

    @NotNull(message = "앨범 발매일은 필수 값 입니다.")
    private LocalDate releasedAt;

    private AlbumType type;
}
