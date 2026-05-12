package com.ratemusic.dto;

import com.ratemusic.entity.Album;
import com.ratemusic.entity.AlbumType;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AlbumResponse {

    private Long id;

    private String title;

    private String artist;

    private String description;

    private String genre;

    private LocalDate releasedAt;

    private AlbumType type;

    private Double averageRate;

    public AlbumResponse(Album album, Double averageRate) {
        this.id = album.getId();
        this.title = album.getTitle();
        this.artist = album.getArtist();
        this.description = album.getDescription();
        this.genre = album.getGenre();
        this.releasedAt = album.getReleasedAt();
        this.type = album.getType();
        this.averageRate = averageRate;
    }
}
