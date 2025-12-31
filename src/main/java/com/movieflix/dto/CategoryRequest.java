package com.movieflix.dto;

import lombok.Builder;

@Builder
public record CategoryRequest(
                String name) {
}
