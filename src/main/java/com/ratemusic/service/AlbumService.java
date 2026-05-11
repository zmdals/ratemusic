package com.ratemusic.service;

import com.ratemusic.dto.AlbumRequest;
import com.ratemusic.dto.AlbumResponse;
import com.ratemusic.entity.Album;
import com.ratemusic.exception.NotFoundException;
import com.ratemusic.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;

    //앨범 생성
    public AlbumResponse create(AlbumRequest request){
        Album album = new Album(request.getTitle(),
                request.getArtist(),
                request.getDescription(),
                request.getGenre(),
                request.getReleasedAt(),
                request.getType());
        return new AlbumResponse(albumRepository.save(album));
    }

    //전체 앨범 조회
    public List<AlbumResponse> getAll(){
        return albumRepository.findAll()
                .stream()
                .map(AlbumResponse::new)
                .collect(Collectors.toList());
    }

    //단건 앨범 조회
    public AlbumResponse getOne(Long id){
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 앨범입니다."));
        return new AlbumResponse(album);
    }

    //앨범 수정
    @Transactional
    public AlbumResponse update(Long id, AlbumRequest request){
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 앨범입니다."));
        album.update(request.getTitle(), request.getArtist(), request.getDescription(),
                request.getGenre(), request.getReleasedAt(), request.getType());
        return new AlbumResponse(album);
    }

    //앨범 삭제
    public void delete(Long id){
        albumRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("존재하지 않는 앨범입니다."));
        albumRepository.deleteById(id);
    }
}
