package com.movieflix.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        var optCategory = categoryService.findById(id);
        if (optCategory.isPresent()) {
            return optCategory.get();
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        var optCategory = categoryService.findById(id);
        if (optCategory.isPresent()) {
            categoryService.deleteCategoryById(id);
            return;
        }
    }
}
