package com.movieflix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.movieflix.model.Streaming;
import com.movieflix.repository.StreamingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository repository;

    public List<Streaming> findAll() {
        return repository.findAll();
    }

    public Streaming save(Streaming streaming) {
        return repository.save(streaming);
    }

    public Optional<Streaming> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        var streaming = findById(id);
        if (streaming == null) {
            throw new RuntimeException("streaming not found with id: " + id);
        }
        repository.deleteById(id);
    }

}
