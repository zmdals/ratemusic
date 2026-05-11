package com.ratemusic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "albums")
@Getter
@NoArgsConstructor
public class Album {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    private String description;

    private String genre;

    @Column(nullable = false)
    private LocalDate releasedAt;

    @Enumerated(EnumType.STRING)
    private AlbumType type;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    private void prePersist(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    public Album(String title, String artist, String description, String genre,
                 LocalDate releasedAt, AlbumType type){
        this.title = title;
        this.artist = artist;
        this.description = description;
        this.genre = genre;
        this.releasedAt = releasedAt;
        this.type = type;
    }

    public void update(String title, String artist, String description, String genre,
                       LocalDate releasedAt, AlbumType type){
        this.title = title;
        this.artist = artist;
        this.description = description;
        this.genre = genre;
        this.releasedAt = releasedAt;
        this.type = type;
    }
}
