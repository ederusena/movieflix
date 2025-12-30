package com.movieflix.dto;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record MovieRequest(
                String title, String description, LocalDate releaseDate, Double rating) {
}
