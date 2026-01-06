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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movieflix/streaming")
@RequiredArgsConstructor
@Tag(name = "Streaming Controller", description = "Endpoints for managing streaming services")
public class StreamingController {

    private final StreamingService service;

    @GetMapping()
    @Operation(summary = "Get All Streaming Services", description = "Retrieve a list of all streaming services")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    public ResponseEntity<List<StreamingResponse>> getAll() {
        List<StreamingResponse> streamingList = service.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
        return ResponseEntity.ok(streamingList);
    }

    @PostMapping()
    @Operation(summary = "Create Streaming Service", description = "Create a new streaming service")
    @ApiResponse(responseCode = "201", description = "Streaming service created successfully", content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    public ResponseEntity<StreamingResponse> save(@Valid @RequestBody StreamingRequest request) {
        Streaming savedStreaming = service.save(StreamingMapper.toStreaming(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StreamingMapper.toStreamingResponse(savedStreaming));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Streaming Service by ID", description = "Retrieve a streaming service by its ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved streaming service", content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    public ResponseEntity<StreamingResponse> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Streaming Service", description = "Delete a streaming service by its ID")
    @ApiResponse(responseCode = "204", description = "Streaming service deleted successfully")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
