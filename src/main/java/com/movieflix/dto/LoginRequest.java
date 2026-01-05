package com.movieflix.dto;

import lombok.Builder;

@Builder
public record LoginRequest(
        String email, String password) {
}
