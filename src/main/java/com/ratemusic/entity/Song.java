package com.ratemusic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "songs")
@Getter
@NoArgsConstructor
public class Song {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    private int trackNumber; //앨범 내 트랙 순서

    private int duration; //곡의 길이 - 초 단위

    public Song(String title, Album album, int trackNumber, int duration){
        this.title = title;
        this.album = album;
        this.trackNumber = trackNumber;
        this.duration = duration;
    }

    public void update(String title, int trackNumber, int duration){
        this.title = title;
        this.trackNumber = trackNumber;
        this.duration = duration;
    }
}
