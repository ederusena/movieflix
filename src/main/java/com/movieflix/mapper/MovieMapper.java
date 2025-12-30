package com.movieflix.mapper;

import com.movieflix.dto.MovieRequest;
import com.movieflix.dto.MovieResponse;
import com.movieflix.model.Movie;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MovieMapper {
    public static MovieResponse toMovieResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate().toString())
                .rating(movie.getRating())
                .createdAt(movie.getCreatedAt().toString())
                .updatedAt(movie.getUpdatedAt().toString())
                .build();
    }

    public static Movie toMovie(MovieRequest request) {
        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .build();
    }
}
