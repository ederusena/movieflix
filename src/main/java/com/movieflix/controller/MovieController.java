package com.movieflix.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movieflix.dto.MovieRequest;
import com.movieflix.dto.MovieResponse;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.model.Movie;
import com.movieflix.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movieflix/movie")
@RequiredArgsConstructor
@Tag(name = "Movie Controller", description = "Endpoints for managing movies")
public class MovieController {

    private final MovieService service;

    @GetMapping()
    @Operation(summary = "Get All Movies", description = "Retrieve a list of all movies")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    public ResponseEntity<List<MovieResponse>> getAll() {
        List<MovieResponse> movieList = service.findAll()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok(movieList);
    }

    @GetMapping("/top")
    @Operation(summary = "Get Top Rated Movies", description = "Retrieve a list of top 5 rated movies")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved top rated movies", content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    public ResponseEntity<List<MovieResponse>> getTopRated() {
        List<MovieResponse> movieList = service.findTop5ByOrderByRatingDesc()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok(movieList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Movie by ID", description = "Retrieve a movie by its ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved movie", content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    public ResponseEntity<MovieResponse> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    // search by category or streaming
    @GetMapping("/search")
    @Operation(summary = "Search Movies", description = "Search movies by category ID or streaming service ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved search results", content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    public ResponseEntity<List<MovieResponse>> search(
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) Long streaming) {

        List<Movie> movies;

        if (category != null) {
            movies = service.findByCategoryId(category);
        } else if (streaming != null) {
            movies = service.findByStreamings(streaming);
        } else {
            movies = service.findAll();
        }

        List<MovieResponse> movieList = movies
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();

        return ResponseEntity.ok(movieList);
    }

    @PostMapping()
    @Operation(summary = "Create Movie", description = "Create a new movie")
    @ApiResponse(responseCode = "201", description = "Movie created successfully", content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest request) {
        Movie savedMovie = service.save(MovieMapper.toMovie(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MovieMapper.toMovieResponse(savedMovie));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Movie", description = "Update an existing movie by its ID")
    @ApiResponse(responseCode = "200", description = "Movie updated successfully", content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MovieRequest request) {
        return service.update(id, MovieMapper.toMovie(request))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Movie", description = "Delete a movie by its ID")
    @ApiResponse(responseCode = "204", description = "Movie deleted successfully")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
