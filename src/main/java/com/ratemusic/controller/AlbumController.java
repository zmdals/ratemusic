package com.ratemusic.controller;

import com.ratemusic.dto.AlbumRequest;
import com.ratemusic.dto.AlbumResponse;
import com.ratemusic.service.AlbumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<AlbumResponse>> getAll(){
        return ResponseEntity.ok(albumService.getAll());
    }

    @PostMapping
    public ResponseEntity<AlbumResponse> create(@Valid @RequestBody AlbumRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(albumService.create(request));
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<AlbumResponse> getOne(@PathVariable Long albumId){
        return ResponseEntity.ok(albumService.getOne(albumId));
    }

    @PutMapping("/{albumId}")
    public ResponseEntity<AlbumResponse> update(@PathVariable Long albumId,
                                                @RequestBody AlbumRequest request){
        return ResponseEntity.ok(albumService.update(albumId, request));
    }

    @DeleteMapping("/{albumId}")
    public ResponseEntity<Void> delete(@PathVariable Long albumId){
        albumService.delete(albumId);
        return ResponseEntity.noContent().build();
    }
}
