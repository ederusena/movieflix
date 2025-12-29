package com.movieflix.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieflix.dto.CategoryRequest;
import com.movieflix.dto.CategoryResponse;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public List<CategoryResponse> getCategories() {
        var categoryList = categoryService.findAll();
        return categoryList.stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
    }

    @PostMapping()
    public CategoryResponse saveCategory(@RequestBody CategoryRequest request) {
        var category = CategoryMapper.toCategory(request);
        var savedCategory = categoryService.saveCategory(category);
        return CategoryMapper.toCategoryResponse(savedCategory);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Long id) {
        var optCategory = categoryService.findById(id);
        if (optCategory.isPresent()) {
            return CategoryMapper.toCategoryResponse(optCategory.get());
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }
}
