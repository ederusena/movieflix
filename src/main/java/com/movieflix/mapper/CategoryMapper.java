package com.movieflix.mapper;

import com.movieflix.dto.CategoryRequest;
import com.movieflix.dto.CategoryResponse;
import com.movieflix.model.Category;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {
    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category toCategory(CategoryRequest request) {
        return Category.builder()
                .name(request.name())
                .build();
    }
}
