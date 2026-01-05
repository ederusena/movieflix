package com.movieflix.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CategoryRequest(
        @NotEmpty(message = "Category name cannot be empty") String name) {
}
