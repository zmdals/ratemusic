package com.ratemusic.controller;

import ch.qos.logback.core.util.StringUtil;
import com.ratemusic.dto.AlbumRequest;
import com.ratemusic.dto.AlbumResponse;
import com.ratemusic.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/albums")
@Tag(name = "Album",description = "앨범 API")
public class AlbumController {

    private final AlbumService albumService;

    @Operation(summary = "앨범 전체 조회", description = "모든 앨범을 조회 후 페이징 처리하여 반환합니다.")
    @GetMapping
    public ResponseEntity<Page<AlbumResponse>> getAll(Pageable pageable){
        return ResponseEntity.ok(albumService.getAll(pageable));
    }

    @Operation(summary = "새 앨범 생성", description = "요청 데이터로 새 앨범을 생성합니다.")
    @PostMapping
    public ResponseEntity<AlbumResponse> create(@Valid @RequestBody AlbumRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(albumService.create(request));
    }

    @Operation(summary = "앨범 단건 조회", description = "albumId로 앨범을 조회합니다.")
    @GetMapping("/{albumId}")
    public ResponseEntity<AlbumResponse> getOne(@PathVariable Long albumId){
        return ResponseEntity.ok(albumService.getOne(albumId));
    }

    @Operation(summary = "앨범 수정", description = "albumId에 해당하는 앨범을 요청 데이터로 수정합니다.")
    @PutMapping("/{albumId}")
    public ResponseEntity<AlbumResponse> update(@PathVariable Long albumId,
                                                @RequestBody AlbumRequest request){
        return ResponseEntity.ok(albumService.update(albumId, request));
    }

    @Operation(summary = "앨범 삭제", description = "albumId에 해당하는 앨범을 삭제합니다.")
    @DeleteMapping("/{albumId}")
    public ResponseEntity<Void> delete(@PathVariable Long albumId){
        albumService.delete(albumId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "앨범 제목 또는 아티스트 검색 / 선택 값:장르",
            description = "검색 키워드를 포함하는 제목 또는 아티스트가 있는 앨범을 장르 값(선택)과 조회 후 페이징 처리하여 반환합니다.")
    @GetMapping("/search")
    public ResponseEntity<Page<AlbumResponse>> search(@RequestParam(value = "keyword") String keyword,
                                                      @RequestParam(value = "genre", required = false) String genre,
                                                      Pageable pageable){
        return ResponseEntity.ok(albumService.search(keyword, genre, pageable));
    }

}
