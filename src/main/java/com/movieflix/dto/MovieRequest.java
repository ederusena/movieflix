package com.movieflix.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;

@Builder
public record MovieRequest(
        String title,
        String description,
        LocalDate releaseDate,
        Double rating,
        List<Long> categories,
        List<Long> streamings) {
}
