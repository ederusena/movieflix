package com.movieflix.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record MovieRequest(
        @Schema(description = "Title of the movie", example = "Inception") @NotEmpty(message = "Title cannot be empty") String title,
        @Schema(description = "Description of the movie", example = "A thief who steals corporate secrets through dream-sharing technology") String description,
        @Schema(description = "Release date of the movie", example = "2023-10-15") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate releaseDate,
        @Schema(description = "Rating of the movie", example = "8.5") Double rating,
        @Schema(description = "List of category IDs") List<Long> categories,
        @Schema(description = "List of streaming IDs") List<Long> streamings) {
}
