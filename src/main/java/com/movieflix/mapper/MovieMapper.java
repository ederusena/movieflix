package com.movieflix.mapper;

import com.movieflix.dto.MovieRequest;
import com.movieflix.dto.MovieResponse;
import com.movieflix.model.Category;
import com.movieflix.model.Movie;
import com.movieflix.model.Streaming;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MovieMapper {
    public static MovieResponse toMovieResponse(Movie movie) {
        var categories = movie
                .getCategories()
                .stream()
                .map(category -> CategoryMapper.toCategoryResponse(category))
                .toList();

        var streaming = movie
                .getStreamings()
                .stream()
                .map(stream -> StreamingMapper.toStreamingResponse(stream))
                .toList();

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categories)
                .streamings(streaming)
                .createdAt(movie.getCreatedAt().toString())
                .updatedAt(movie.getUpdatedAt().toString())
                .build();
    }

    public static Movie toMovie(MovieRequest request) {

        var categories = request
                .categories()
                .stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        var streaming = request
                .streamings()
                .stream()
                .map(stream -> Streaming.builder().id(stream).build())
                .toList();

        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .categories(categories)
                .streamings(streaming)
                .build();
    }
}
