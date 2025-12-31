package com.movieflix.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.movieflix.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findMovieByTitle(String movie);

    @Query("SELECT m FROM Movie m JOIN m.categories c WHERE c.id = :categoryId")
    List<Movie> findMovieByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT m FROM Movie m JOIN m.streamings s WHERE s.id = :streamingId")
    List<Movie> findMovieByStreamings(@Param("streamingId") Long streamingId);

    List<Movie> findTop5ByOrderByRatingDesc();
}
