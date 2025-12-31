package com.movieflix.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        var categories = categoryService.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
        return ResponseEntity.ok(categories);
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest request) {
        var category = CategoryMapper.toCategory(request);
        var savedCategory = categoryService.saveCategory(category);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoryMapper.toCategoryResponse(savedCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
