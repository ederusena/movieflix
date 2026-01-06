package com.movieflix.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record LoginRequest(
        @Schema(description = "Email of the user", example = "user@example.com") String email,
        @Schema(description = "Password of the user", example = "password123") String password) {
}
