package com.movieflix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.movieflix.model.Category;
import com.movieflix.model.Movie;
import com.movieflix.model.Streaming;
import com.movieflix.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie save(Movie movie) {
        var streamings = this.findStreamings(movie.getStreamings());
        var categories = this.findCategories(movie.getCategories());

        movie.setCategories(categories);
        movie.setStreamings(streamings);

        return movieRepository.save(movie);
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public void deleteById(Long id) {
        var movie = findById(id);
        if (movie == null) {
            throw new RuntimeException("Movie not found with id: " + id);
        }
        movieRepository.deleteById(id);
    }

    private List<Category> findCategories(List<Category> categories) {
        return categories.stream()
                .map(cat -> categoryService.findById(cat.getId())
                        .orElseThrow(() -> new RuntimeException("Category not found with id: " + cat.getId())))
                .toList();
    }

    private List<Streaming> findStreamings(List<Streaming> streamings) {
        return streamings.stream()
                .map(stream -> streamingService.findById(stream.getId())
                        .orElseThrow(() -> new RuntimeException("Streaming not found with id: " + stream.getId())))
                .toList();
    }
}