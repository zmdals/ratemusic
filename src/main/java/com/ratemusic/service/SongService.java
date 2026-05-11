package com.ratemusic.service;

import com.ratemusic.dto.SongRequest;
import com.ratemusic.dto.SongResponse;
import com.ratemusic.entity.Album;
import com.ratemusic.entity.Song;
import com.ratemusic.exception.NotFoundException;
import com.ratemusic.repository.AlbumRepository;
import com.ratemusic.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    @Transactional(readOnly = true)
    public List<SongResponse> getAllSongs(Long id) {
        return songRepository.findByAlbumId(id)
                .stream()
                .map(SongResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SongResponse getOneSong(Long songId){
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 곡 입니다."));
        return new SongResponse(song);
    }

    @Transactional
    public SongResponse createSong(Long albumId, SongRequest request){
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 앨범입니다."));
        Song song = new Song(request.getTitle(),
                album,
                request.getTrackNumber(),
                request.getDuration());
        return new SongResponse(songRepository.save(song));
    }

    @Transactional
    public SongResponse updateSong(Long songId, SongRequest request){
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 곡 입니다."));
        song.update(request.getTitle(), request.getTrackNumber(), request.getDuration());
        return new SongResponse(song);
    }

    public void deleteSong(Long songId){
        songRepository.findById(songId)
                        .orElseThrow(() -> new NotFoundException("존재하지 않는 곡 입니다."));
        songRepository.deleteById(songId);
    }
}
