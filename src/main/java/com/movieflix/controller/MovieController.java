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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    @GetMapping()
    public ResponseEntity<List<MovieResponse>> getAll() {
        List<MovieResponse> movieList = service.findAll()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok(movieList);
    }

    @GetMapping("/top")
    public ResponseEntity<List<MovieResponse>> getTopRated() {
        List<MovieResponse> movieList = service.findTop5ByOrderByRatingDesc()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok(movieList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    // search by category or streaming
    @GetMapping("/search")
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
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest request) {
        Movie savedMovie = service.save(MovieMapper.toMovie(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MovieMapper.toMovieResponse(savedMovie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @RequestBody MovieRequest request) {
        return service.update(id, MovieMapper.toMovie(request))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
