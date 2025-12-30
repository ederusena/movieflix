package com.movieflix.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;

@Builder
public record MovieResponse(
                Long id,
                String title,
                String description,
                LocalDate releaseDate,
                Double rating,
                List<CategoryResponse> categories,
                List<StreamingResponse> streamings,
                String createdAt,
                String updatedAt) {

}
