package com.ratemusic.repository;

import com.ratemusic.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song,Long> {
    List<Song> findByAlbumId(Long id);
}
