package com.movieflix.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieflix.model.Category;
import com.movieflix.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

    @PostMapping()
    public void saveCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
    }
}
