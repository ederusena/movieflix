package com.movieflix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.movieflix.model.Movie;
import com.movieflix.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie save(Movie movie) {
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

}
