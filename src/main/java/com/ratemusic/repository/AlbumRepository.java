package com.ratemusic.repository;

import com.ratemusic.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album,Long> {
    @Query("select a from Album a " +
            "where (a.title like concat('%',:keyword,'%') or a.artist like concat('%',:keyword,'%')) " +
            "and (:genre is null or a.genre = :genre)")
    Page<Album> findByTitleOrArtistWithGenre(@Param("keyword") String keyword, @Param("genre") String genre,
                                             Pageable pageable);

}

