package com.movieflix.dto;

import lombok.Builder;

@Builder
public record CategoryResponse(Long id, String name) {

}
