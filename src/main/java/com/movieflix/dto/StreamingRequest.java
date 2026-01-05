package com.movieflix.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record StreamingRequest(
        @NotEmpty(message = "Streaming name cannot be empty") String name) {
}
