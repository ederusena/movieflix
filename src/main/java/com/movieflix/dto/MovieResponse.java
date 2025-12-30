package com.movieflix.dto;

import lombok.Builder;

@Builder
public record MovieResponse(
        Long id,
        String title,
        String description,
        String releaseDate,
        Double rating,
        String createdAt,
        String updatedAt) {

}
