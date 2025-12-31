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

    public Optional<Movie> update(Long id, Movie movie) {
        var existingMovie = findById(id);
        if (existingMovie.isPresent()) {
            var toUpdate = existingMovie.get();
            toUpdate.setTitle(movie.getTitle());
            toUpdate.setDescription(movie.getDescription());
            toUpdate.setReleaseDate(movie.getReleaseDate());
            toUpdate.setRating(movie.getRating());

            var streamings = this.findStreamings(movie.getStreamings());
            var categories = this.findCategories(movie.getCategories());

            toUpdate.getCategories().clear();
            toUpdate.getStreamings().clear();
            toUpdate.getCategories().addAll(categories);
            toUpdate.getStreamings().addAll(streamings);

            movieRepository.save(toUpdate);
            return Optional.of(toUpdate);
        }
        return Optional.empty();

    }

    public List<Movie> findByCategoryId(Long categoryId) {
        return movieRepository.findMovieByCategoryId(categoryId);
    }

    public List<Movie> findByStreamings(Long streaming) {
        return movieRepository.findMovieByStreamings(streaming);
    }
}