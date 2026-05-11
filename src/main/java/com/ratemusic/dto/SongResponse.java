package com.ratemusic.dto;

import com.ratemusic.entity.Song;
import lombok.Getter;

@Getter
public class SongResponse {
    private Long id;
    private String title;
    private String artist;
    private int trackNumber; //앨범 내 트랙 순서
    private int duration; //곡의 길이 - 초 단위

    public SongResponse(Song song){
        this.id = song.getId();
        this.title = song.getTitle();
        this.artist = song.getAlbum().getArtist();
        this.trackNumber = song.getTrackNumber();
        this.duration = song.getDuration();
    }
}
