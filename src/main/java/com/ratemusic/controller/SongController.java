package com.ratemusic.controller;

import com.ratemusic.dto.SongRequest;
import com.ratemusic.dto.SongResponse;
import com.ratemusic.service.SongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums/{albumId}/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @PostMapping
    public ResponseEntity<SongResponse> createSong(@Valid @RequestBody SongRequest request,
                                                              @PathVariable Long albumId){
        return ResponseEntity.status(HttpStatus.CREATED).body(songService.createSong(albumId,request));
    }

    @GetMapping
    public ResponseEntity<List<SongResponse>> getAllSongs(@PathVariable Long albumId){
        return ResponseEntity.ok(songService.getAllSongs(albumId));
    }

    @GetMapping("/{songId}")
    public ResponseEntity<SongResponse> getOneSong(@PathVariable Long songId){
        return ResponseEntity.ok(songService.getOneSong(songId));
    }

    @PutMapping("/{songId}")
    public ResponseEntity<SongResponse> updateSong(@PathVariable Long songId,
                                                   @RequestBody SongRequest request){
        return ResponseEntity.ok(songService.updateSong(songId,request));
    }
    
    @DeleteMapping("/{songId}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long songId){
        songService.deleteSong(songId);
        return ResponseEntity.noContent().build();
    }
}
