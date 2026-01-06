package com.movieflix.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record UserRequest(
        @Schema(description = "Name of the user", example = "John Doe") String name,
        @Schema(description = "Email of the user", example = "email@email.com") String email,
        @Schema(description = "Password of the user", example = "password123") String password) {
}
