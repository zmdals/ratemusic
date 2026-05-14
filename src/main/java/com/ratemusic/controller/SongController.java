package com.ratemusic.controller;

import com.ratemusic.dto.SongRequest;
import com.ratemusic.dto.SongResponse;
import com.ratemusic.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums/{albumId}/songs")
@RequiredArgsConstructor
@Tag(name = "Song", description = "곡 API")
public class SongController {

    private final SongService songService;

    @Operation(summary = "앨범 곡 생성", description = "요청 albumId 앨범 수록곡을 생성합니다.")
    @PostMapping
    public ResponseEntity<SongResponse> createSong(@Valid @RequestBody SongRequest request,
                                                              @PathVariable Long albumId){
        return ResponseEntity.status(HttpStatus.CREATED).body(songService.createSong(albumId,request));
    }

    @Operation(summary = "앨범 수록곡 전체 조회", description = "요청 albumId 앨범 수록곡 전체 조회합니다.")
    @GetMapping
    public ResponseEntity<List<SongResponse>> getAllSongs(@PathVariable Long albumId){
        return ResponseEntity.ok(songService.getAllSongs(albumId));
    }

    @Operation(summary = "앨범 수록곡 단건(상세) 조회", description = "요청 albumId 앨범의 songId와 일치하는 곡을 조회합니다.")
    @GetMapping("/{songId}")
    public ResponseEntity<SongResponse> getOneSong(@PathVariable Long songId){
        return ResponseEntity.ok(songService.getOneSong(songId));
    }

    @Operation(summary = "앨범 수록곡 수정", description = "요청 albumId 앨범의 songId와 일치하는 곡을 수정합니다.")
    @PutMapping("/{songId}")
    public ResponseEntity<SongResponse> updateSong(@PathVariable Long songId,
                                                   @RequestBody SongRequest request){
        return ResponseEntity.ok(songService.updateSong(songId,request));
    }

    @Operation(summary = "앨범 수록곡 삭제", description = "요청 albumId 앨범의 songId와 일치하는 곡을 삭제합니다.")
    @DeleteMapping("/{songId}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long songId){
        songService.deleteSong(songId);
        return ResponseEntity.noContent().build();
    }
}
