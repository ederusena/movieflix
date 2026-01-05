package com.movieflix.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieflix.dto.StreamingRequest;
import com.movieflix.dto.StreamingResponse;
import com.movieflix.mapper.StreamingMapper;
import com.movieflix.model.Streaming;
import com.movieflix.service.StreamingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService service;

    @GetMapping()
    public ResponseEntity<List<StreamingResponse>> getAll() {
        List<StreamingResponse> streamingList = service.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
        return ResponseEntity.ok(streamingList);
    }

    @PostMapping()
    public ResponseEntity<StreamingResponse> save(@Valid @RequestBody StreamingRequest request) {
        Streaming savedStreaming = service.save(StreamingMapper.toStreaming(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StreamingMapper.toStreamingResponse(savedStreaming));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
